"""import numpy as np
import cv2 as cv3

img = cv3.imread("image.png", cv3.IMREAD_COLOR)

cv3.line(img, (0,0), (150,150), (255,255,255), 15)

cv3.imshow('image',img)
cv3.waitKey(0)
cv3.destroyAllWindows()
"""

import cv2
import numpy as np
filename = 'image2.png'
img = cv2.imread(filename)
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
gray = np.float32(gray)
dst = cv2.cornerHarris(gray,2,3,0.04)
#result is dilated for marking the corners, not important
dst = cv2.dilate(dst,None)
# Threshold for an optimal value, it may vary depending on the image.
img[dst>0.01*dst.max()]=[0,0,255]
cv2.imwrite("harris2.png",img)
cv2.imshow('dst',img)
if cv2.waitKey(0) & 0xff == 27:
    cv2.destroyAllWindows()
