SELECT m.Value, t.MsgText, t.MsgTip
FROM AD_Message_Trl t, AD_Message m
WHERE m.AD_Message_ID=t.AD_Message_ID
 AND t.AD_Language=?