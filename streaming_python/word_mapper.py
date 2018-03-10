#!/usr/bin/python
import sys


# def read_in():
#     lines = sys.stdin.readlines()
#     for i in range(len(lines)):
#         lines[i] = lines[i].replace('\n','')
#     #print lines
#     return lines

for line in sys.stdin:
	line.strip
	for word in line.split():
		print '%s\t%s' % (word[0], len(word))