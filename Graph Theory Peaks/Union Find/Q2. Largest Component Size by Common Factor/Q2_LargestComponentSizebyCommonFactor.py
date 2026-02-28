class Solution(object):
    def largestComponentSize(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        import collections

        # Step 1: Generate prime numbers up to sqrt(max(nums))
        # We only need primes up to sqrt(max value) for factorization
        primes = []
        for x in range(2, int(max(nums) ** 0.5) + 1):
            for y in primes:
                # If x is divisible by any existing prime, it's not prime
                if x % y == 0:
                    break
            else:
                # If no divisor found, x is prime
                primes.append(x)

        # Step 2: Compute prime factors for each number
        # factors[a] will store unique prime factors of number a
        factors = collections.defaultdict(list)

        for a in nums:
            x = a  # Copy of number for factorization

            # Try dividing by all primes
            for p in primes:
                # If p^2 > x, no further factorization needed
                if p * p > x:
                    break

                # If p divides x, it's a prime factor
                if x % p == 0:
                    factors[a].append(p)

                    # Remove all occurrences of p from x
                    while x % p == 0:
                        x //= p

            # If remainder x > 1, it is also a prime factor
            if x > 1:
                factors[a].append(x)
                primes.append(x)  # Add new discovered prime

        # Remove duplicate primes
        primes = list(set(primes))
        n = len(primes)

        # Map each prime to an index (for union-find)
        p2i = {p: i for i, p in enumerate(primes)}

        # Step 3: Initialize Union-Find (DSU) on primes
        parent = [i for i in range(n)]

        # Find function with path compression
        def find(i):
            if i != parent[i]:
                parent[i] = find(parent[i])  # Compress path
            return parent[i]

        # Union two prime indices
        def union(i, j):
            pi, pj = find(i), find(j)
            if pi != pj:
                parent[pi] = pj  # Merge sets

        # Step 4: Connect primes that appear in same number
        for a in nums:
            if factors[a]:  # If number has prime factors
                p0 = factors[a][0]  # Take first prime factor

                # Union first prime with all other primes of same number
                for p in factors[a][1:]:
                    union(p2i[p0], p2i[p])

        # Step 5: Count how many numbers belong to each connected prime component
        # Each number corresponds to the root of its first prime factor
        count = collections.Counter(
            find(p2i[factors[a][0]]) for a in nums if factors[a]
        )

        # Return size of largest connected component
        return max(count.values())