// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class BuddyClimb extends SubsystemBase {

  private static final int buddyLeftId = 10;
  private static final int buddyRightId = 9;

  private boolean m_isBuddyClimbActivated = false;

  private CANSparkMax m_buddyLeft = new CANSparkMax(buddyLeftId, MotorType.kBrushless);
  private CANSparkMax m_buddyRight = new CANSparkMax(buddyRightId, MotorType.kBrushless);

  private Servo m_yeetScrew = new Servo(0);

  /** Creates a new BuddyClimb. */
  public BuddyClimb() {

    m_buddyLeft.restoreFactoryDefaults();
    m_buddyRight.restoreFactoryDefaults();

    m_buddyLeft.setInverted(true);

    m_buddyLeft.burnFlash();
    m_buddyRight.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println(RobotContainer.getPilot().getLeftTriggerAxis());

    if (m_isBuddyClimbActivated) {
      m_buddyLeft.set(
          RobotContainer.getPilot().getLeftTriggerAxis()
              - RobotContainer.getPilot().getRightTriggerAxis());
      m_buddyRight.set(
          RobotContainer.getPilot().getLeftTriggerAxis()
              - RobotContainer.getPilot().getRightTriggerAxis());
    }
  }

  public Command activate() {
    return new SequentialCommandGroup(
        this.runOnce(
            () -> {
              m_isBuddyClimbActivated = true;
            }),
        this.runOnce(() -> m_yeetScrew.setRaw(1))
            .andThen(new WaitCommand(15).andThen(() -> m_yeetScrew.setRaw(0))));
  }
}
