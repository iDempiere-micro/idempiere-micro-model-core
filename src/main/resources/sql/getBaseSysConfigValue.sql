SELECT Value
FROM AD_SysConfig
WHERE Name=?
  AND AD_Client_ID IN (0, ?)
  AND AD_Org_ID IN (0, ?)
  AND IsActive = 'Y'
ORDER BY AD_Client_ID DESC, AD_Org_ID DESC
