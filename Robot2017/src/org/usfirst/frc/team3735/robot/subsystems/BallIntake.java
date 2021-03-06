package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.ballintake.BallIntakeRollerOff;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */

public class BallIntake extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon motor;
	private Setting speed;
	
	public BallIntake(){
		motor = new CANTalon(RobotMap.BallIntake.roller);
		motor.setInverted(RobotMap.BallIntake.rollerInverted);
		
    	speed = new Setting("Ball Intake setSpeed", Constants.BallIntake.rollerInSpeed, false);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new BallIntakeRollerOff());
    }
    
    public void setRollerCurrent(double speed){
    	motor.set(speed);
    }
    public void setRollerSmartDashboard(){
    	setRollerCurrent(speed.getValue());
    }
    
    public double getPower(){
    	return Math.abs(motor.getOutputVoltage() * motor.getOutputCurrent());
    }
    
    public void log(){
    	//SmartDashboard.putNumber("Ball Intake getPower", getPower());
    }

	public void debugLog() {
		// TODO Auto-generated method stub
		
	}
}

