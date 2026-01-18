class Solution(object):
    def detectCapitalUse(self, word):
        """
        :type word: str
        :rtype: bool
        """
        if len(word) < 2:                      # If word has 0 or 1 letter
            return True                        # Always valid

        if word[0].isupper() and word[1].isupper():  # If first two letters are uppercase
            for i in range(2, len(word)):      # Check remaining letters
                if word[i].islower():           # If any letter is lowercase
                    return False                # Invalid capital usage
        else:
            for i in range(1, len(word)):      # Check from second letter
                if word[i].isupper():           # If any letter is uppercase
                    return False                # Invalid capital usage

        return True                             # Capital usage is correct
