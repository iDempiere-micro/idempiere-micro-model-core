package org.compiere.model;

public interface IPaymentProcessor {
  void initialize(I_C_BankAccount_Processor mbap, PaymentInterface mp);

  boolean processCC();
}
