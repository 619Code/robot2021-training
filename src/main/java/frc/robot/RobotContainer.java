// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.LogCommand;
import frc.robot.commands.SetMotorSpeedCommand;
import frc.robot.subsystems.LoggingSubsystem;
import frc.robot.subsystems.MainMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final LoggingSubsystem loggingSubsystem = new LoggingSubsystem();

  private final LogCommand bButtonDown = new LogCommand(loggingSubsystem, "B Button Down");

  private XboxController driverController;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    var mainMotorSubsystem = new MainMotorSubsystem();
    driverController = new XboxController(0);
    
    // Configure the button bindings
    mainMotorSubsystem.setDefaultCommand(new SetMotorSpeedCommand(mainMotorSubsystem, driverController));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    System.out.println("Configuring Bindings");
    JoystickButton bButton = new JoystickButton(driverController, XboxController.Button.kB.value);
    bButton.whileHeld(bButtonDown);    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return bButtonDown;
  }
}
