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

public class GTAOI implements DriveOI{

	public XboxController main;
	public XboxController co;

	public GTAOI() {

		main = new XboxController(0);
		co = new XboxController(1);

		//Baby Driver
		//main.pov180.whenPressed(new DriveGoToPeg());
		main.pov90.whileHeld(new DriveAddSensitiveRight());
		main.pov270.whileHeld(new DriveAddSensitiveLeft());


		main.b.whenPressed(new GearIntakeDropOff());
		main.a.whileHeld(new GearIntakeFeeding());
//		main.x.whileHeld(new DriveAddSensitiveLeft());
//		main.y.whileHeld(new DriveAddSensitiveRight());

		main.start.whenPressed(new DriveChangeToGearDirection());
		main.back.whenPressed(new DriveChangeToBallDirection());
		
		
		//CoDriver
		co.y.whenPressed(new ScalerUp(1));
		co.x.whenPressed(new ScalerOff());
		co.a.whileHeld(new GearIntakeRollersIn());
		co.b.whileHeld(new GearIntakeRollersOut());

		co.pov0.whenPressed(new ShooterAgitatorOn(31000, 10));
		co.pov90.whenPressed(new ShooterAgitatorOn(28000, 10));
		co.pov180.whenPressed(new ShooterAgitatorOn(25000, 10));
		co.pov270.whenPressed(new ShooterAgitatorOff());
		
		co.lt.whenPressed(new BallIntakeRollerOff());
		co.lb.whenPressed(new BallIntakeRollerIn());
		
		co.start.whenPressed(new InterruptOperations());
		
		
		
	}
	
	
	public double getDriveMove() {
		return (main.getRightTrigger() - main.getLeftTrigger());
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		return main.getLeftX();
		//return main.getRightX();
	}
	
	@Override
	public double getFODMag() {
		return main.getRightMagnitude();
		//return 0;
	}
	
	public double getFODAngle(){
		return main.getRightAngle();
		//return 0;
	}

	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .1 || Math.abs(getDriveTurn()) > .1;
	}

	
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}




}
