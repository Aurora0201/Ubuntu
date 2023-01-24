import cv2
import numpy as np
import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = ['SimHei']
pic1 = cv2.imread('15.jpg')
pic_rgb = cv2.cvtColor(pic1, cv2.COLOR_BGR2RGB)
pic2 = cv2.cvtColor(pic1, cv2.COLOR_BGR2GRAY)
# Roberts算子
x = np.array([[-1, 0], [0, 1]], dtype=int)
y = np.array([[0, -1], [1, 0]], dtype=int)
x1 = cv2.filter2D(pic2, cv2.CV_16S, x)
y1 = cv2.filter2D(pic2, cv2.CV_16S, y)
xx1 = cv2.convertScaleAbs(x1)
yy1 = cv2.convertScaleAbs(y1)
Roberts = cv2.addWeighted(xx1, 0.5, yy1, 0.5, 0)
plt.subplot(121), plt.imshow(pic_rgb), plt.title('初始图像'), plt.axis('off')
plt.subplot(122), plt.imshow(Roberts, cmap=plt.cm.gray), plt.title('Roberts算子'), plt.axis('off')
plt.show()
# Prewitt算子
x = np.array([[1, 1, 1], [0, 0, 0], [-1, -1, -1]], dtype=int)
y = np.array([[-1, 0, 1], [-1, 0, 1], [-1, 0, 1]], dtype=int)
x2 = cv2.filter2D(pic2, cv2.CV_16S, x)
y2 = cv2.filter2D(pic2, cv2.CV_16S, y)
xx2 = cv2.convertScaleAbs(x2)
yy2 = cv2.convertScaleAbs(y2)
Prewitt = cv2.addWeighted(xx2, 0.5, yy2, 0.5, 0)
plt.subplot(121), plt.imshow(pic_rgb), plt.title('初始图像'), plt.axis('off')
plt.subplot(122), plt.imshow(Prewitt, cmap=plt.cm.gray), plt.title('Prewitt算子'), plt.axis('off')
plt.show()
# Sobel算子
x3 = cv2.Sobel(pic2, cv2.CV_16S, 1, 0)
y3 = cv2.Sobel(pic2, cv2.CV_16S, 0, 1)
xx3 = cv2.convertScaleAbs(x3)
yy3 = cv2.convertScaleAbs(y3)
Sobel = cv2.addWeighted(xx3, 0.5, yy3, 0.5, 0)
plt.subplot(121),plt.imshow(pic_rgb),plt.title('初始图像'), plt.axis('off')
plt.subplot(122),plt.imshow(Sobel, cmap=plt.cm.gray ),plt.title('Sobel算子'), plt.axis('off')
plt.show()
