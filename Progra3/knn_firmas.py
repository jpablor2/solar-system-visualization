import numpy as np
import cv2
from matplotlib import pyplot as plt
img = cv2.imread('firmas_e.png')
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

# Now we split the image to 5000 cells, each 20x20 size
cells = [np.hsplit(row,5) for row in np.vsplit(gray,4)]

# Make it into a Numpy array. It size will be (50,100,20,20)
x = np.array(cells)

# Now we prepare train_data and test_data.
train = x[:,:4].reshape(-1,100).astype(np.float32) # Size = (2500,400)
test = x[:,4:5].reshape(-1,100).astype(np.float32) # Size = (2500,400)

# Create labels for train and test data
k = np.arange(1)
train_labels = np.repeat(k,10)[:,np.newaxis]
test_labels = train_labels.copy()

# Initiate kNN, train the data, then test it with test data for k=1
knn = cv2.ml.KNearest_create()
knn.train(train, cv2.ml.ROW_SAMPLE, train_labels)
ret,result,neighbours,dist = knn.findNearest(test,k=5)

# Now we check the accuracy of classification
# For that, compare the result with test_labels and check which are wrong
matches = result==test_labels
correct = np.count_nonzero(matches)
accuracy = correct*100.0/result.size
print (accuracy)

