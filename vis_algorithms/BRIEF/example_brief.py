import numpy as np
import cv2
from matplotlib import pyplot as plt
img = cv2.imread('image5.jpeg',0)
# Initiate FAST detector
star = cv2.xfeatures2d.StarDetector_create()
# Initiate BRIEF extractor
brief = cv2.xfeatures2d.BriefDescriptorExtractor_create()
# find the keypoints with STAR
kp = star.detect(img,None)
# compute the descriptors with BRIEF
kp, des = brief.compute(img, kp)
img3 = cv2.drawKeypoints(img, kp, None, color=(255,0,0))
cv2.imwrite('brief2.png',img3)
print (brief.descriptorSize())
print (des.shape)
