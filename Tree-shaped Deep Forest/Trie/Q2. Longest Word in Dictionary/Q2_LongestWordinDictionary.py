class Solution(object):
    def longestWord(self, words):
        # Initialize answer as empty string
        ans = ""
        
        # Convert list to set for O(1) lookup
        wordset = set(words)

        # Iterate through each word in the list
        for word in words:
            
            # Check if:
            # 1. Current word is longer than answer OR
            # 2. Same length but lexicographically smaller
            if len(word) > len(ans) or (len(word) == len(ans) and word < ans):
                
                # Check if ALL prefixes of the word exist in the set
                # Example: for "apple" → "a", "ap", "app", "appl" must exist
                if all(word[:k] in wordset for k in xrange(1, len(word))):
                    
                    # If valid, update the answer
                    ans = word

        # Return the final result
        return ans