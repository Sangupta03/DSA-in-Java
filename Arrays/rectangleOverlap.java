//remember to convert rec1 and rec2 arr into edges
class rectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //[0] - x1(left edge)
        //[1] -y1(bottom edge)
        //[2] -x2(right edge)
        //[3] -y2(top edge)

        //above are four cases when rectangles dont overlap
        //we do so so because in many cases they can overlap
        //easier to find cases when they dont overlap
        //EDGE CASE IS WHEN THE BOUNDARY LINE OF RECTANGLES ARE SAME
        //DOES NOT MEAN THAT THEY OVERLAP


        if(rec1[2]<=rec2[0] || //too far left rec1 is
           rec1[0]>=rec2[2] || //too far right rec1 is
           rec1[1]>=rec2[3] || //too far at top rec1 is
           rec1[3]<=rec2[1]){  //too far at bottom rec1 
            return false; //dont overlap
           }
        return true; //mean overlap since none of condition true
    }
}