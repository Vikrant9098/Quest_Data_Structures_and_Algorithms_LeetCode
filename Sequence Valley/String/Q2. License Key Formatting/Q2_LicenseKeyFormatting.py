class Solution(object):
    def licenseKeyFormatting(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        # Remove all dashes
        s = s.replace('-', '')

        head = len(s) % k                # Size of the first group
        
        grouping = []                    # List to store groups
        
        # Handle the first group if it exists
        if head:
            grouping.append(s[:head])
        
        # Create remaining groups of size k
        for index in range(head, len(s), k):
            grouping.append(s[index:index + k])
        
        # Join groups with '-' and convert to uppercase
        return '-'.join(grouping).upper()
