package org.usfirst.frc.team3735.robot.ois;

//import org.usfirst.frc.team3735.robot.commands.DriveTurnToAngleHyperbola;
import org.usfirst.frc.team3735.robot.commands.*;
import org.usfirst.frc.team3735.robot.commands.ballintake.*;
import org.usfirst.frc.team3735.robot.commands.drive.*;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveChangeToBallDirection;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveChangeToGearDirection;
import org.usfirst.frc.team3735.robot.commands.drive.spinnyspin.DriveSpinMove;
import org.usfirst.frc.team3735.robot.commands.drive.turntoangle.DriveTurnToAnglePIDCtrl;
import org.usfirst.frc.team3735.robot.commands.drive.turntoangle.DriveTurnToAnglePIDCtrlVision;
import org.usfirst.frc.team3735.robot.commands.gearintake.*;
import org.usfirst.frc.team3735.robot.commands.scaler.*;
import org.usfirst.frc.team3735.robot.commands.sequences.DriveGoToPeg;
import org.usfirst.frc.team3735.robot.commands.sequences.GearIntakeDropOff;
import org.usfirst.frc.team3735.robot.commands.shooter.*;
import org.usfirst.frc.team3735.robot.commands.vision.*;


import org.usfirst.frc.team3735.robot.subsystems.Vision.Pipes;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.JoystickPOVButton;
import org.usfirst.frc.team3735.robot.util.oi.JoystickTriggerButton;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DemoOI implements DriveOI{

	public XboxController main;
	public XboxController co;
	
	private boolean driveEnabled = true;
	public DemoOI() {

		main = new XboxController(0);
		co = new XboxController(1);

		//Baby Driver
		
		main.lt.whenPressed(new GearIntakeDropOff());
		main.rt.whileHeld(new GearIntakeFeeding());
		
		//CoDriver
		co.pov0.whenPressed(new ShooterAgitatorOn(31000, 10));
		co.pov90.whenPressed(new ShooterAgitatorOn(28000, 10));
		co.pov180.whenPressed(new ShooterAgitatorOn(25000, 10));
		co.pov270.whenPressed(new ShooterAgitatorOff());
		
		co.start.whenPressed(new InterruptOperations());
		
		co.a.whileHeld(new GearIntakeRollersIn());
		co.b.whileHeld(new GearIntakeRollersOut());

		co.lt.whenPressed(new BallIntakeRollerOff());
		co.lb.whenPressed(new BallIntakeRollerIn());
		
		co.x.whenPressed(new InstantCommand(){
			@Override
			public void initialize(){
				driveEnabled = false;
			}
		});
		co.y.whenPressed(new InstantCommand(){
			@Override
			public void initialize(){
				driveEnabled = true;
			}
		});

	}
	
	@Override
	public double getDriveMove() {
		return driveEnabled ? main.getLeftY() : 0;
	}

	@Override
	public double getDriveTurn() {
		return driveEnabled ? main.getRightX() : 0;
	}
	
	@Override
	public double getFODMag() {
		return 0;
	}
	
	@Override
	public double getFODAngle(){
		return 0;
	}

	@Override
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .1 || Math.abs(getDriveTurn()) > .1;
	}

	@Override
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}




}
