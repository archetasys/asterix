#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <netinet/in.h>
#include <net/ethernet.h>
#include <pcap.h>
#include "asterix.h"

#define UDP_HDR_LEN 8
#define SECONDS2NANOS 1000000000
#define MICROS2NANOS 1000

typedef struct pcap_stats_s
{
	uint64_t pkt_count;
	uint64_t tcp_count;
	uint64_t udp_count;
} pcap_stats_t;

typedef struct pcap_data_s
{
	// ast_parser_t *parser;
	pcap_stats_t stats;
} pcap_data_t;

static void on_packet(u_char *data, const struct pcap_pkthdr *hdr, const u_char *pkt)
{
	pcap_data_t *d = (pcap_data_t *)data;
	if (NULL != d)
	{
		d->stats.pkt_count++;
	}

	struct ether_header *eth_hdr = (struct ether_header *)pkt;
	if (ntohs(eth_hdr->ether_type) != ETHERTYPE_IP)
	{
		return;
	}

	/* Find start of IP header */
	const u_char *ip_hdr = pkt + ETHER_HDR_LEN;

	/* The second-half of the first byte in ip_header contains the IP header
	   length (IHL). The IHL is number of 32-bit segments. Multiply by four to
	   get a byte count for pointer arithmetic. */
	const size_t ip_hdr_len = ((*ip_hdr) & 0x0F) << 2;

	/* Now that we know where the IP header is, we can inspect the IP header
	   for a protocol number to make sure it is TCP before going any further.
	   Protocol is always the 10th byte of the IP header. */
	const u_char proto = *(ip_hdr + 9);
	if (proto == IPPROTO_TCP)
	{
		/* Add the ethernet and ip header length to the start of the packet to
		   find the beginning of the TCP header. */
		const u_char *tcp_hdr = pkt + ETHER_HDR_LEN + ip_hdr_len;

		/* TCP header length is stored in the first half of the 12th byte in
		   the TCP header. Because we only want the value of the top half of the
		   byte, we have to shift it down to the bottom half otherwise it is
		   using the most significant bits instead of the least significant bits.
		   The TCP header length stored in those 4 bits represents how many
		   32-bit words there are in the header, just like the IP header length.
		   We multiply by four again to get a byte count. */
		const size_t tcp_hdr_len = (((*(tcp_hdr + 12)) & 0xF0) >> 4) << 2;

		/* Add up all the header sizes to find the payload offset. */
		const size_t hdr_len = ETHER_HDR_LEN + ip_hdr_len + tcp_hdr_len;

		if (NULL != d)
		{
			d->stats.tcp_count++;
		}

		const u_char *payload = tcp_hdr + tcp_hdr_len;
		const size_t payload_len = hdr->caplen - hdr_len;
		if (NULL != d)
		{
			// ast_parser_parse(d->parser, (uint8_t *)payload, 0, payload_len, NULL, NULL, NULL);
		}
	}
	else if (proto == IPPROTO_UDP)
	{
		/* Add the ethernet and ip header length to the start of the packet to find the
		   beginning of the UDP header. */
		const u_char *udp_hdr = pkt + ETHER_HDR_LEN + ip_hdr_len;

		/* Add up all the header sizes to find the payload offset. */
		const size_t hdr_len = ETHER_HDR_LEN + ip_hdr_len + UDP_HDR_LEN;

		if (NULL != d)
		{
			d->stats.udp_count++;
		}

		const u_char *payload = udp_hdr + UDP_HDR_LEN;
		const size_t payload_len = hdr->caplen - hdr_len;
		if (NULL != d)
		{
			// ast_parser_parse(d->parser, (uint8_t *)payload, 0, payload_len, NULL, NULL, NULL);
		}
	}
}

int main(int argc, char **argv)
{
	if (argc < 2)
	{
		fprintf(stderr, "Usage: astpcap <file>\n");
		return 1;
	}

	const char *filename = argv[1];
	char errbuf[PCAP_ERRBUF_SIZE];

	printf("%s\n", ast_version_full());
	printf("asterix version %d.%d.%d\n",
		ast_version_major(), ast_version_minor(), ast_version_patch());
	printf("%d %s\n", ast_error_code(), ast_error_message());

	// if (ast_log_init(NULL))
	// {
	// 	fprintf(stderr, "failed init log");
	// 	return 1;
	// }

	pcap_data_t data;
	memset(&data.stats, 0, sizeof(pcap_stats_t));
	// if (ast_parser_init(&data.parser, 0) < 0)
	// {
	// 	fprintf(stderr, "failed initialize parser, %s\n", ast_error_message());
	// 	return 1;
	// }

	pcap_t *hpcap = pcap_open_offline(filename, errbuf);
	if (hpcap == NULL)
	{
		fprintf(stderr, "Error open PCAP file: %s\n", filename);
		return 1;
	}

	struct timespec ts0;
	struct timespec ts1;
	clock_gettime(CLOCK_MONOTONIC, &ts0);
	if (pcap_loop(hpcap, 0, on_packet, (u_char *)&data) == PCAP_ERROR)
	{
		pcap_close(hpcap);
		fprintf(stderr, "err pcap_loop: %s", pcap_geterr(hpcap));
	}
	clock_gettime(CLOCK_MONOTONIC, &ts1);
	const long dt = ts1.tv_sec * SECONDS2NANOS + ts1.tv_nsec -
					(ts0.tv_sec * SECONDS2NANOS + ts0.tv_nsec);

	printf("pkt count: %" PRIu64 "\n", data.stats.pkt_count);
	printf("tcp count: %" PRIu64 "\n", data.stats.tcp_count);
	printf("udp count: %" PRIu64 "\n", data.stats.udp_count);
	printf(" duration: %ld us\n", dt / MICROS2NANOS);

	pcap_close(hpcap);
	// ast_parser_destroy(data.parser);
	// ast_log_exit();
	return 0;
}
