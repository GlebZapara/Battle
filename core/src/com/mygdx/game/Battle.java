package com.mygdx.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Battle {
	public static void main(String[] args) {
		try {
			// Загрузка изображения
			BufferedImage image = ImageIO.read(new File("qwer.png"));

			// Получение ширины и высоты изображения
			int width = image.getWidth();
			int height = image.getHeight();

			// Проход по каждому пикселю изображения
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					// Получение цвета пикселя
					int rgb = image.getRGB(x, y);
					Color color = new Color(rgb);

					// Проверка, является ли цвет синим
					if (color.equals(Color.BLUE)) {
						// Замена цвета на красный
						Color newColor = Color.RED;
						image.setRGB(x, y, newColor.getRGB());
					}
				}
			}

			// Сохранение измененного изображения
			File outputFile = new File("qwer.png");
			ImageIO.write(image, "png", outputFile);

			System.out.println("Изображение успешно изменено и сохранено.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

