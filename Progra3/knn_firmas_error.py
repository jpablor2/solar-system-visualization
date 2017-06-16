import time
import numpy as np
import cv2
from matplotlib import pyplot as plt
img = cv2.imread('firmas_50_p.png')
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

# Now we split the image to 20 cells, each 400x400 size
cells = [np.hsplit(row,5) for row in np.vsplit(gray,8)]

# Make it into a Numpy array. It size will be (4,5,400,400)
x = np.array(cells)

# Now we prepare train_data and test_data.
train = x[:,:3].reshape(-1,400).astype(np.float32) # Size = (10,160000)
test = x[:,3:5].reshape(-1,400).astype(np.float32) # Size = (10,160000)

# Create labels for train and test data
k = np.arange(2)
train_labels = np.repeat(k,12)[:,np.newaxis]
test_labels = np.repeat(k,8)[:,np.newaxis]

# Initiate kNN, train the data, then test it with test data for k=1
knn = cv2.ml.KNearest_create()
start = time.time()

knn.train(train, cv2.ml.ROW_SAMPLE, train_labels)

end = time.time()
training = end - start
start = time.time()

ret,result,neighbours,dist = knn.findNearest(test,k=10)

end = time.time()
testing = end - start

# Now we check the accuracy of classification
# For that, compare the result with test_labels and check which are wrong
matches = result==test_labels
correct = np.count_nonzero(matches)
accuracy = correct*100.0/result.size
print (accuracy)
print ("Training time: "+str(training))
print ("Testing time: "+str(testing))
