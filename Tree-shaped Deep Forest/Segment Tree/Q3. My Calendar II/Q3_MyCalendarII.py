class MyCalendarTwo(object):

    def __init__(self):
        self.bookings = []          # store all bookings
        self.overlap_bookings = []  # store double bookings (overlaps)

    def book(self, startTime, endTime):
        """
        :type startTime: int
        :type endTime: int
        :rtype: bool
        """

        # check if it causes triple booking
        for booking in self.overlap_bookings:  # loop through double bookings
            if self.does_overlap(booking[0], booking[1], startTime, endTime):  # check overlap
                return False  # triple booking → reject

        # create new double bookings if overlap with existing bookings
        for booking in self.bookings:  # loop through all bookings
            if self.does_overlap(booking[0], booking[1], startTime, endTime):  # check overlap
                self.overlap_bookings.append(  # add overlap interval
                    self.get_overlapped(booking[0], booking[1], startTime, endTime)  # get overlap
                )

        # add current booking
        self.bookings.append((startTime, endTime))  # store booking

        return True  # booking successful

    # check if two intervals overlap
    def does_overlap(self, start1, end1, start2, end2):
        return max(start1, start2) < min(end1, end2)  # overlap condition

    # get overlapping interval
    def get_overlapped(self, start1, end1, start2, end2):
        return max(start1, start2), min(end1, end2)  # return overlap range