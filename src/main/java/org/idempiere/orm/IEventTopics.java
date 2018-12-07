package org.idempiere.orm;

/** @author hengsin */
public interface IEventTopics {

  String MODEL_EVENT_PREFIX = "adempiere/po/";
  /** Model Change Type New */
  String PO_BEFORE_NEW = MODEL_EVENT_PREFIX + "beforeNew";

  String PO_AFTER_NEW = MODEL_EVENT_PREFIX + "afterNew";
  String PO_AFTER_NEW_REPLICATION = MODEL_EVENT_PREFIX + "afterNewReplication"; // @Trifon
  /** Model Change Type Change */
  String PO_BEFORE_CHANGE = MODEL_EVENT_PREFIX + "beforeChange";

  String PO_AFTER_CHANGE = MODEL_EVENT_PREFIX + "afterChange";
  String PO_AFTER_CHANGE_REPLICATION = MODEL_EVENT_PREFIX + "afterChangeReplication"; // @Trifon
  /** Model Change Type Delete */
  String PO_BEFORE_DELETE = MODEL_EVENT_PREFIX + "beforeDelete";

  String PO_AFTER_DELETE = MODEL_EVENT_PREFIX + "afterDelete";
  String PO_BEFORE_DELETE_REPLICATION = MODEL_EVENT_PREFIX + "beforeDeleteReplication"; // @Trifon
  // asynchrous model event
  String PO_POST_CREATE = MODEL_EVENT_PREFIX + "postCreate";
  String PO_POST_UPADTE = MODEL_EVENT_PREFIX + "postUpdate";
  String PO_POST_DELETE = MODEL_EVENT_PREFIX + "postDelete";
  String PO_ALL = MODEL_EVENT_PREFIX + "*";

  String DOC_EVENT_PREFIX = "adempiere/doc/";
  /** Called before document is prepared */
  String DOC_BEFORE_PREPARE = DOC_EVENT_PREFIX + "beforePrepare";
  /** Called before document is void */
  String DOC_BEFORE_VOID = DOC_EVENT_PREFIX + "beforeVoid";
  /** Called before document is close */
  String DOC_BEFORE_CLOSE = DOC_EVENT_PREFIX + "beforeClose";
  /** Called before document is reactivate */
  String DOC_BEFORE_REACTIVATE = DOC_EVENT_PREFIX + "beforeReactivate";
  /** Called before document is reversecorrect */
  String DOC_BEFORE_REVERSECORRECT = DOC_EVENT_PREFIX + "beforeReverseCorrect";
  /** Called before document is reverseaccrual */
  String DOC_BEFORE_REVERSEACCRUAL = DOC_EVENT_PREFIX + "beforeReverseAccrual";
  /** Called before document is completed */
  String DOC_BEFORE_COMPLETE = DOC_EVENT_PREFIX + "beforeComplete";
  /** Called after document is prepared */
  String DOC_AFTER_PREPARE = DOC_EVENT_PREFIX + "afterPrepare";
  /** Called after document is completed */
  String DOC_AFTER_COMPLETE = DOC_EVENT_PREFIX + "afterComplete";
  /** Called after document is void */
  String DOC_AFTER_VOID = DOC_EVENT_PREFIX + "afterVoid";
  /** Called after document is closed */
  String DOC_AFTER_CLOSE = DOC_EVENT_PREFIX + "afterClose";
  /** Called after document is reactivated */
  String DOC_AFTER_REACTIVATE = DOC_EVENT_PREFIX + "afterReactivate";
  /** Called after document is reversecorrect */
  String DOC_AFTER_REVERSECORRECT = DOC_EVENT_PREFIX + "afterReverseCorrect";
  /** Called after document is reverseaccrual */
  String DOC_AFTER_REVERSEACCRUAL = DOC_EVENT_PREFIX + "afterReverseAccrual";
  /** Called before document is posted */
  String DOC_BEFORE_POST = DOC_EVENT_PREFIX + "beforePost";
  /** Called after document is posted */
  String DOC_AFTER_POST = DOC_EVENT_PREFIX + "afterPost";

  String DOC_ALL = DOC_EVENT_PREFIX + "*";

  String AFTER_LOGIN = "adempiere/afterLogin";

  String ACCT_FACTS_VALIDATE = "adempiere/acct/factsValidate";

  /** Import Events * */
  String IMPORT_PREFIX = "adempiere/import/";
  /** Event triggered before all import records are validated */
  String IMPORT_BEFORE_VALIDATE = IMPORT_PREFIX + "beforeValidate";
  /** Event triggered after all import records are validated */
  String IMPORT_AFTER_VALIDATE = IMPORT_PREFIX + "afterValidate";
  /** Event triggered before an import record is processed */
  String IMPORT_BEFORE_IMPORT = IMPORT_PREFIX + "beforeImport";
  /** Event triggered after an import record is processed */
  String IMPORT_AFTER_IMPORT = IMPORT_PREFIX + "afterImport";

  String PREF_AFTER_LOAD = "adempiere/pref/afterLoad";

  /** Called after next document actions are set */
  String DOCACTION = "adempiere/docAction";

  String BROADCAST_MESSAGE = "idempiere/broadcastMsg";

  String REQUEST_SEND_EMAIL = "idempiere/requestSendEMail";
}
