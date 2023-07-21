package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

public class Starter extends Game {
	SpriteBatch batch;
	Player1 player1;
	Player2 player2;
	Random random;
	BitmapFont font;
	int totalDamage1;
	int totalDamage2;
	float damageTime1;
	float damageTime2;
	float healthTime1;
	float healthTime2;
	float armorTime1;
	float armorTime2;

	public boolean getGameScreen() {
		return GameScreen;
	}

	public void setGameScreen(boolean gameScreen) {
		this.GameScreen = gameScreen;
	}

	private boolean GameScreen = false;

	public void create() {
		batch = new SpriteBatch();
		random = new Random();
		font = new BitmapFont();
		player1 = new Player1(27, 20, random.nextInt(100) + 1, 100, 100, "Player1");
		player2 = new Player2(1601, 20, random.nextInt(100) + 1, 100, 100, "Player2");
		totalDamage1 = 0;
		totalDamage2 = 0;
		damageTime1 = 0;
		damageTime2 = 0;
		healthTime1 = 0;
		healthTime2 = 0;
		armorTime1 = 0;
		armorTime2 = 0;

		showMainMenu();
		setScreen(new GameScreen(this));
	}

	public void render() {

		boolean isScreen = getGameScreen();

		if (!isScreen) {
			setGameScreen(true);
		}

		super.render();
		Texture backgroundTexture = new Texture(Gdx.files.internal("background.png"));
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		player1.render(batch);
		player2.render(batch);

		if (player2.health <= 0) {
			font.setColor(0, 0, 0, healthTime2);
		} else if (healthTime2 > 0) {
			font.setColor(0, 1, 0, healthTime2);
		} else {
			font.setColor(1, 1, 1, 1);
		}
		font.draw(batch, "Health: " + player2.health, 1823, 361);

		if (player1.health <= 0) {
			font.setColor(0, 0, 0, healthTime1);
		} else if (healthTime1 > 0) {
			font.setColor(0, 1, 0, healthTime1);
		} else {
			font.setColor(1, 1, 1, 1);
		}
		font.draw(batch, "Health: " + player1.health, 20, 361);

		if (damageTime2 >= 0) {
			font.setColor(1, 0, 0, damageTime2);
			font.draw(batch, "Damage: " + totalDamage2, 20, 381);
			font.setColor(1, 1, 1, 1);
		} else {
			font.setColor(1, 1, 1, 1);
			font.draw(batch, "Damage: " + totalDamage2, 20, 381);
		}

		if (damageTime1 >= 0) {
			font.setColor(1, 0, 0, damageTime1);
			font.draw(batch, "Damage: " + totalDamage1, 1830, 381);
			font.setColor(1, 1, 1, 1);
		} else {
			font.setColor(1, 1, 1, 1);
			font.draw(batch, "Damage: " + totalDamage1, 1830, 381);
		}

		if (armorTime2 > 0) {
			font.setColor(0, 0, 1, armorTime2);
		} else {
			font.setColor(1, 1, 1, 1);
		}
		font.draw(batch, "Armor: " + player2.armor, 1830, 341);

		if (armorTime1 > 0) {
			font.setColor(0, 0, 1, armorTime1);
		} else {
			font.setColor(1, 1, 1, 1);
		}
		font.draw(batch, "Armor: " + player1.armor, 20, 341);

		batch.end();

		if (player1.health > 0 && player2.health > 0) {
			int damage = random.nextInt(player1.attack) + 1;
			int damageDifference;
			if (player2.armor > 0) {
				damageDifference = Math.min(player2.armor, damage);

				player2.armor -= damageDifference;
				totalDamage2 += damageDifference;
				healthTime1 = 1;
				damageTime1 = 1;
				armorTime2 = 1;
				System.out.println(player1.name + " attacks " + player2.name + " and deals " + damageDifference + " damage to armor.");
			} else {
				damageDifference = damage;
				if (player2.armor == 0) {
					healthTime1 = 1;
					damageTime1 = 1;
					armorTime2 = 1;
				}
//				player1 = new Player1(1601, 20);
				player2.health -= damageDifference;
				totalDamage2 += damageDifference;
				System.out.println(player1.name + " attacks " + player2.name + " and deals " + damageDifference + " damage.");
			}

			if (player2.health <= 0) {
				System.out.println(player1.name + " Wins!!!");
				return;
			}
//			player1 = new Player1(1601, 20);
			damage = random.nextInt(player2.attack) + 1;
			if (player1.armor > 0) {
				damageDifference = Math.min(player1.armor, damage);

				player1.armor -= damageDifference;
				totalDamage1 += damageDifference;
				healthTime2 = 1;
				damageTime2 = 1;
				armorTime1 = 1;
				System.out.println(player2.name + " attacks " + player1.name + " and deals " + damageDifference + " damage to armor.");
			} else {
				damageDifference = damage;
				if (player1.armor == 0) {
					healthTime2 = 1;
					damageTime2 = 1;
					armorTime1 = 1;
				}

				player1.health -= damageDifference;
				totalDamage1 += damageDifference;
				System.out.println(player2.name + " attacks " + player1.name + " and deals " + damageDifference + " damage.");
			}

			if (player1.health <= 0) {
				System.out.println(player2.name + " Wins!!!");
			}
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void showMainMenu() {
		setScreen(new GameScreen(this));
	}

	public void dispose() {
		super.dispose();
		batch.dispose();
		player1.dispose();
		player2.dispose();
		font.dispose();
	}
}