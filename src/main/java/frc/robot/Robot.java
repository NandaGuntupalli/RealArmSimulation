// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Arm;

public class Robot extends TimedRobot {

  private final Arm m_arm = new Arm();
  private final CommandXboxController armController = new CommandXboxController(0);

  @Override
  public void robotInit() {}

  @Override
  public void simulationPeriodic() {
    m_arm.simulationPeriodic();

    Trigger armSetPoint = new Trigger(armController.a());

    if (!armSetPoint.getAsBoolean()) {
      // Here, we run PID control like normal.
      m_arm.reachSetpoint();
    } else {
      // Otherwise, we disable the motor.
      m_arm.stop();
    }
  }

  @Override
  public void teleopInit() {
    m_arm.loadPreferences();
  }

  @Override
  public void teleopPeriodic() {
  Trigger armSetPoint = new Trigger(armController.a());

    if (armSetPoint.getAsBoolean()) {
      // Here, we run PID control like normal.
      m_arm.reachSetpoint();
    } else {
      // Otherwise, we disable the motor.
      m_arm.stop();
    }
  }

  @Override
  public void close() {
    m_arm.close();
    super.close();
  }

  @Override
  public void disabledInit() {
    // This just makes sure that our simulation code knows that the motor's off.
    m_arm.stop();
  }
}