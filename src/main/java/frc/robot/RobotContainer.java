// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TicTacToe;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Screw;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
 
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
 
  private final Screw screw;
  private final TicTacToe zero;
  private final TicTacToe box;
  private final TicTacToe bottom;
  private final TicTacToe middle;
  private final TicTacToe top;
  private final TicTacToe teleOp;
  private final DriveTrain driveTrain;
  private final Joystick joy1;
  private final Joystick joy2;
  private final ArcadeDrive arcadeDrive;

 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    joy1 = new Joystick(Constants.USBOrder.Zero);
    joy2 = new Joystick(Constants.USBOrder.One);
    screw = new Screw();
    zero = new TicTacToe(screw, 0, joy2, false);
    teleOp = new TicTacToe(screw, 0, joy2, true);
    box = new TicTacToe(screw, Constants.ScrewConsts.boxPos, joy2, false);
    bottom = new TicTacToe(screw, Constants.ScrewConsts.bottomPos, joy2, false);
    middle = new TicTacToe(screw, Constants.ScrewConsts.middlePos, joy2, false);
    top = new TicTacToe(screw, Constants.ScrewConsts.topPos, joy2, false);
    driveTrain = new DriveTrain();
    arcadeDrive = new ArcadeDrive(driveTrain, joy1);
 
    driveTrain.setDefaultCommand(arcadeDrive);
    screw.setPosition(0);
    screw.setDefaultCommand(teleOp);
    configureButtonBindings();
  }
 
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton zeroButton = new JoystickButton(joy2, 1);
    JoystickButton boxButton = new JoystickButton(joy2, 3);
    JoystickButton bottomButton = new JoystickButton(joy2, 4);
    JoystickButton middleButton = new JoystickButton(joy2, 5);
    JoystickButton topButton = new JoystickButton(joy2, 6);
 
    zeroButton.whenPressed(zero);
    boxButton.whenPressed(box);
    bottomButton.whenPressed(bottom);
    middleButton.whenPressed(middle);
    topButton.whenPressed(top);
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
