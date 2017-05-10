import cv2
import numpy as np

img = cv2.imread('image5.jpeg',0)
# Create SURF object. You can specify params here or later.
# Here I set Hessian Threshold to 400
surf = cv2.xfeatures2d.SURF_create(400)
# Find keypoints and descriptors directly
kp, des = surf.detectAndCompute(img,None)
surf.setHessianThreshold(500)
# Again compute keypoints and check its
kp, des = surf.detectAndCompute(img,None)
img2 = cv2.drawKeypoints(img,kp,None,(255,0,0),4)
cv2.imwrite('surf2.jpg',img2)
