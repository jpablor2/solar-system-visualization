import numpy as np
import cv2
from matplotlib import pyplot as plt
img1 = cv2.imread('image6.jpeg',0)          # queryImage
img2 = cv2.imread('image5.jpeg',0) # trainImage
# Initiate SIFT detector
sift = cv2.xfeatures2d.SURF_create()
# find the keypoints and descriptors with SIFT
kp1, des1 = sift.detectAndCompute(img1,None)
kp2, des2 = sift.detectAndCompute(img2,None)
# BFMatcher with default params
bf = cv2.BFMatcher()
matches = bf.knnMatch(des1,des2, k=2)
# Apply ratio test
good = []
for m,n in matches:
    if m.distance < 0.75*n.distance:
        good.append([m])
# cv2.drawMatchesKnn expects list of lists as matches.
img3 = cv2.drawMatchesKnn(img1,kp1,img2,kp2,good[:30],2)
cv2.imwrite('matches6.png',img3)
