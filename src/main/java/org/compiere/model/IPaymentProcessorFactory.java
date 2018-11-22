package org.compiere.model;

/** @author hengsin */
public interface IPaymentProcessorFactory {

  /**
   * @param className
   * @return payment processor instance
   */
  public IPaymentProcessor newPaymentProcessorInstance(String className);
}
