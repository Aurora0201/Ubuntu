import matplotlib.pyplot as plt
import cv2

image_0 = cv2.imread('15.jpg',cv2.IMREAD_GRAYSCALE)
plt.subplot(121)
plt.imshow(image_0, vmin=0, vmax=255, cmap='gray')
plt.subplot(122)
plt.imshow(image_0, vmin=0, vmax=255, cmap='Greens')
plt.show()
