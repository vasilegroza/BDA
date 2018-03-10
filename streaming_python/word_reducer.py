#!/usr/bin/python
import sys


previous_key = None
acumulator = 0
counter = 0
for line in sys.stdin:
	line.strip
	(key, value) = line.split('\t')
	if not previous_key:
		previous_key = key
	

	if key != previous_key:
		print '%s\t%s' % (previous_key, str(acumulator*1.0/counter))
		acumulator, counter = 0, 0		
		previous_key = key

	acumulator += int(value)
	counter += 1
print  '%s\t%s' % (previous_key, str(acumulator*1.0/counter))