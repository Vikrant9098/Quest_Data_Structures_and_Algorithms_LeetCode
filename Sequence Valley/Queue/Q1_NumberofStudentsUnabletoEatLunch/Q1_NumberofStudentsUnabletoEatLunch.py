class Solution(object):
    def countStudents(self, students, sandwiches):
        st0 = students.count(0)  # Count number of 0s
        st1 = len(students) - st0  # Remaining students are 1s

        for x in sandwiches:
            if x == 1:  # square sandwich
                if st1 > 0:         #If there are students who want a square sandwich
                    st1 -= 1        #allocate to him and remove him

                else:               #If no student wants a square sandwich, No other sandwich 
                    return st0+st1  #in the stack can be allocated without allocating this sandwich
                                    
                    
            else:       #circular sandwich
                if st0 > 0:         # If there are students who wants circular sandwich
                    st0 -= 1        # allocate to him and remove him

                else:               # If no student wants a circular sandwich, No other sandwich 
                    return st0+st1  # in the stack can be allocated without allocating this sandwich

        return 0  # All sandwiches distributed successfully