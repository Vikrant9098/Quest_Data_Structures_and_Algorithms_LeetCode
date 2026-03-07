from collections import deque


class Solution(object):
    def watchedVideosByFriends(self, watchedVideos, friends, id, level):
        """
        :type watchedVideos: List[List[str]]
        :type friends: List[List[int]]
        :type id: int
        :type level: int
        :rtype: List[str]
        """

        visited = set()              # Set to track visited friends
        queue = deque()              # Queue for BFS traversal

        queue.append((id, 0))        # Start BFS from given person with level 0
        visited.add(id)              # Mark starting person as visited
        
        level_friends = []           # Store friends exactly at the given level
        
        # Perform BFS
        while queue:
            curr_id, curr_level = queue.popleft()   # Get current person and level

            # If we reached the required level
            if curr_level == level:
                level_friends.append(curr_id)       # Add this friend to the list

            # If current level is less than target level, continue exploring
            elif curr_level < level:
                for friend in friends[curr_id]:     # Check all friends of current person

                    # If friend not visited yet
                    if friend not in visited:
                        visited.add(friend)         # Mark friend as visited
                        queue.append((friend, curr_level + 1))  # Add friend with next level

        video_count = Counter()      # Dictionary to count video frequencies

        # Count videos watched by friends at the required level
        for friend in level_friends:
            for video in watchedVideos[friend]:
                video_count[video] += 1   # Increase count for this video
        
        # Sort videos:
        # 1. By increasing frequency
        # 2. If same frequency, by lexicographical order
        return sorted(video_count.keys(), key=lambda x: (video_count[x], x))