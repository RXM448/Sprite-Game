package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

//fx:controller="application.SampleController">

public class SampleController {

	double locationX , locationY, score = 0, playerX = 16, playerY = 16, goalX = 21, goalY = 19;

	boolean playing = false;

	@FXML
	Button  Start, Reset;

	@FXML
	Label Label_player, Goal, Label_score, Label_Spike1, Label_Spike2, Label_Spike3, Label_Spike4;



	Spike Spike1 = new Spike(0, 0);
	Spike Spike2 = new Spike(0, 0);
	Spike Spike3 = new Spike(0, 0);
	Spike Spike4 = new Spike(0, 0);
	Spike[] spikes = new Spike[]{Spike1, Spike2, Spike3, Spike4};

	ArrayList<Double> initial = new ArrayList<Double>();
	
	// Initialises the board when the start button is pressed 
	@FXML
	void startGame() {
		
		//gives all spikes random x and y velocities 
		for (Spike element : spikes) {
			element.setXVol((Math.random() * 20) - 10);
			element.setYVol((Math.random() * 20) - 10);
		}
		
		//sets playing boolean to true, allowing player movement
		playing = true;

		//starts movement of the spikes 
		moveSpikes();
		//thread1.start();
		//thread2.start();
		//thread3.start();
		//thread4.start();   

	}

	

	@SuppressWarnings("deprecation")
	@FXML
	void resetGame() {
	
		playing = false;
		//thread1.stop();
		Spike1 = new Spike(0, 0);
		Spike2 = new Spike(0, 0);
		Spike3 = new Spike(0, 0);
		Spike4 = new Spike(0, 0);
		spikes = new Spike[]{Spike1, Spike2, Spike3, Spike4};
		
		Label_score.setText("0");
		
		Label_player.setLayoutX(initial.get(0)); Label_player.setLayoutY(initial.get(1)); 
		Goal.setLayoutX(initial.get(2)); Goal.setLayoutY(initial.get(3));  
		Label_Spike1.setLayoutX(initial.get(4)); Label_Spike1.setLayoutY(initial.get(5));  
		Label_Spike2.setLayoutX(initial.get(6)); Label_Spike2.setLayoutY(initial.get(7));   
		Label_Spike3.setLayoutX(initial.get(8)); Label_Spike3.setLayoutY(initial.get(9));   
		Label_Spike4.setLayoutX(initial.get(10)); Label_Spike4.setLayoutY(initial.get(11));  
	}
		


	//updates current location of player icon 
	private void updateBounds() {
		locationX = Label_player.getLayoutX();
		locationY = Label_player.getLayoutY();

	}


	//Checks if the player icon and goal icon currently intersect 
	private void checkGoal() {

		double PYTop = Label_player.getLayoutY();
		double PYBottom = PYTop + playerY;
		double PXLeft = Label_player.getLayoutX();
		double PXRright = PXLeft + playerX;

		double GYTop = Goal.getLayoutY();
		double GYBottom = GYTop + goalY;
		double GXLeft = Goal.getLayoutX();
		double GXRight = GXLeft + goalX;

		if(Check.checkColission(GXLeft, GXRight, GYTop, GYBottom, PXLeft, PXRright, PYTop, PYBottom)) {
			score = score + 1;
			Label_score.setText(String.valueOf(score));
			relocateGoal();
		}

	}

	//checks if any of the spikes have collided with the player, and calls lose functions if so 
	private void CheckSpikecollisions() {

		double PYTop = Label_player.getLayoutY();
		double PYBottom = PYTop + playerY;
		double PXLeft = Label_player.getLayoutX();
		double PXRright = PXLeft + playerX;

		
			for (Spike element : spikes) {
				double SYTop = element.getYCoord();
				double SYBottom = SYTop + goalY;
				double SXLeft = element.getXCoord();
				double SXRight = SXLeft + goalX;

				if(Check.checkColission(SXLeft, SXRight, SYTop, SYBottom, PXLeft, PXRright, PYTop, PYBottom)) {
					youLose();
				}
			}
		


	}

	//ends the game by changing playing to false and printing text indicating loss 
	private void youLose() {
		playing = false;
		System.out.print("YOU LOSE, GOOD DAY SIR!");


	}


	//moves the goal to a new location
	private void relocateGoal() {
		Goal.setLayoutX(Math.random() * 400 + 10);
		Goal.setLayoutY(Math.random() * 200 + 10);
	}


	//moves the player icon up the screen 
	@FXML	
	void move_up(){
		if(playing == true) {
			if(Label_player.getLayoutY() > 10) {
				Label_player.setLayoutY(Label_player.getLayoutY() - 10 );
			}
			updateBounds();
			checkGoal();
		}
	}	


	//moves the player icon down the screen 
	@FXML
	void move_down(){
		if(playing == true) {
			if(Label_player.getLayoutY() < 280) {
				Label_player.setLayoutY(Label_player.getLayoutY() + 10 );
			}
			updateBounds();
			checkGoal();
		}	
	}
	
	//moves the player icon left along the screen 
	@FXML
	void move_left(){
		if(playing == true) {
			if(Label_player.getLayoutX() > 10) {
				Label_player.setLayoutX(Label_player.getLayoutX() - 10 );
			}
			updateBounds();
			checkGoal();
		}
	}	
	
	//moves the player icon right along the screen 
	@FXML
	void move_right(){
		if(playing == true) {
			if(Label_player.getLayoutX() < 560) {
				Label_player.setLayoutX(Label_player.getLayoutX() + 10);
			}
			updateBounds();
			checkGoal();
		}
	}
	
	private void moveSpikes() {
		
		
	
	//thread that moves the spikes around the screen 
	Thread thread1 = new Thread(){
		
		@SuppressWarnings("deprecation")
		public void run(){
			double LSX1 = Label_Spike1.getLayoutX(), LSY1 = Label_Spike1.getLayoutY();
			double LSX2 = Label_Spike2.getLayoutX(), LSY2 = Label_Spike2.getLayoutY();
			double LSX3 = Label_Spike3.getLayoutX(), LSY3 = Label_Spike3.getLayoutY();
			double LSX4 = Label_Spike4.getLayoutX(), LSY4 = Label_Spike4.getLayoutY();

			//records all initial positions
			initial.add(Label_player.getLayoutX()); initial.add(1, Label_player.getLayoutY());
			initial.add(2, Goal.getLayoutX()); initial.add(3, Goal.getLayoutY());
			initial.add(4, LSX1); initial.add(5, LSY1);
			initial.add(6, LSX2); initial.add(7, LSY2);
			initial.add(8, LSX3); initial.add(9, LSY3);
			initial.add(10, LSX4); initial.add(11, LSY4);
			
			
			while(playing == true) {
				try {
					Thread.sleep(25);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//moves each spike according to its velocity, then updates their current coordinates
				
				Label_Spike1.setLayoutX(LSX1 + Spike1.getXVol());
				Label_Spike1.setLayoutY(LSY1 + Spike1.getYVol());
				Spike1.setXCoord(Label_Spike1.getLayoutX());
				Spike1.setYCoord(Label_Spike1.getLayoutY());

				Label_Spike2.setLayoutX(LSX2 + Spike2.getXVol());
				Label_Spike2.setLayoutY(LSY2 + Spike2.getYVol());
				Spike2.setXCoord(Label_Spike2.getLayoutX());
				Spike2.setYCoord(Label_Spike2.getLayoutY());

				Label_Spike3.setLayoutX(LSX3 + Spike3.getXVol());
				Label_Spike3.setLayoutY(LSY3 + Spike3.getYVol());
				Spike3.setXCoord(Label_Spike3.getLayoutX());
				Spike3.setYCoord(Label_Spike3.getLayoutY());

				Label_Spike4.setLayoutX(LSX4 + Spike4.getXVol());
				Label_Spike4.setLayoutY(LSY4 + Spike4.getYVol());
				Spike4.setXCoord(Label_Spike4.getLayoutX());
				Spike4.setYCoord(Label_Spike4.getLayoutY());

				LSX1 = Label_Spike1.getLayoutX();
				LSY1 = Label_Spike1.getLayoutY();

				LSX2 = Label_Spike2.getLayoutX();
				LSY2 = Label_Spike2.getLayoutY();

				LSX3 = Label_Spike3.getLayoutX();
				LSY3 = Label_Spike3.getLayoutY();

				LSX4 = Label_Spike4.getLayoutX();
				LSY4 = Label_Spike4.getLayoutY();

				//checks that the spike is not about to go out of bounds, reversing its velocity if so
				switch(Check.SpikeBoundry(LSX1, LSY1)) {
				case 0: break;
				case 1: Spike1.setXVol(- Spike1.getXVol()); break;
				case 2: Spike1.setYVol(- Spike1.getYVol()); break;
				}
				switch(Check.SpikeBoundry(LSX2, LSY2)) {
				case 0: break;
				case 1: Spike2.setXVol(- Spike2.getXVol()); break;
				case 2: Spike2.setYVol(- Spike2.getYVol()); break;
				}
				switch(Check.SpikeBoundry(LSX3, LSY3)) {
				case 0: break;
				case 1: Spike3.setXVol(- Spike3.getXVol()); break;
				case 2: Spike3.setYVol(- Spike3.getYVol()); break;
				}
				switch(Check.SpikeBoundry(LSX4, LSY4)) {
				case 0: break;
				case 1: Spike4.setXVol(- Spike4.getXVol()); break;
				case 2: Spike4.setYVol(- Spike4.getYVol()); break;


				}
				
				//hecks to see if each spikes movement has caused it to collide with the player 
				CheckSpikecollisions();
				
			}
			
			this.stop();
		}


	};
	thread1.start();
	}

	Thread thread2 = new Thread(){
		public void run(){
			double LSX = 0, LSY =0;
			while(true) {
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LSX = Label_Spike2.getLayoutX();
				LSY = Label_Spike2.getLayoutY();

				Label_Spike2.setLayoutX(LSX + Spike2.getXVol());
				Label_Spike2.setLayoutY(LSY + Spike2.getYVol());

				LSX = Label_Spike2.getLayoutX();
				LSY = Label_Spike2.getLayoutY();

				switch(Check.SpikeBoundry(LSX, LSY)) {
				case 0: break;
				case 1: Spike2.setXVol(- Spike2.getXVol()); break;
				case 2: Spike2.setYVol(- Spike2.getYVol()); break;

				}
			}
		}
	};

	Thread thread3 = new Thread(){
		public void run(){
			double LSX = 0, LSY =0;
			while(true) {
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LSX = Label_Spike3.getLayoutX();
				LSY = Label_Spike3.getLayoutY();

				Label_Spike3.setLayoutX(LSX + Spike3.getXVol());
				Label_Spike3.setLayoutY(LSY + Spike3.getYVol());

				LSX = Label_Spike3.getLayoutX();
				LSY = Label_Spike3.getLayoutY();

				switch(Check.SpikeBoundry(LSX, LSY)) {
				case 0: break;
				case 1: Spike3.setXVol(- Spike3.getXVol()); break;
				case 2: Spike3.setYVol(- Spike3.getYVol()); break;

				}
			}
		}
	};

	Thread thread4 = new Thread(){
		public void run(){

			double LSX = 0, LSY =0;
			while(true) {
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LSX = Label_Spike4.getLayoutX();
				LSY = Label_Spike4.getLayoutY();


				Label_Spike4.setLayoutX(LSX + Spike4.getXVol());
				Label_Spike4.setLayoutY(LSY + Spike4.getYVol());

				LSX = Label_Spike4.getLayoutX();
				LSY = Label_Spike4.getLayoutY();

				switch(Check.SpikeBoundry(LSX, LSY)) {
				case 0: break;
				case 1: Spike4.setXVol(- Spike4.getXVol()); break;
				case 2: Spike4.setYVol(- Spike4.getYVol()); break;

				}
			}
		}
	};
}

