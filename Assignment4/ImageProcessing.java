import acm.graphics.*;
import acm.program.*;

public class ImageProcessing extends GraphicsProgram {
	
	
	//Margin on the left side of the window
	private static final int marginY = 10;
	
	public void run() {
		GImage image = new GImage("SkyLine.jpg");
		GImage flippedImage = flipHorizontal(image);
		
		//Scale each image to 80%
		image.scale(.8);
		flippedImage.scale(.8);
		
		//Calculate the coordinates for images
		int marginX = (int) (getWidth()/2 - image.getWidth()/2);
		int secondImageY = (int) (marginY*2 + image.getHeight());
		
		//Print images
		add(image, marginX, marginY);
		add(flippedImage, marginX, secondImageY);
	}
	

	private GImage flipHorizontal(GImage image) {
		//create pixel image array
		int[][] preImage = image.getPixelArray();
		
		int height = preImage.length;
		int width = preImage[0].length;
		
		int[][] newImage = new int[height][width];
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				int pixel = preImage[i][j];
				newImage[i][width-j-1] = pixel; 
			}
		}
			
		//create flipped image
		GImage flippedImage = new GImage(newImage);
			
		return flippedImage;
	}
	
}
