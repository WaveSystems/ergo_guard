Const HKLM = &H80000002 'HKEY_LOCAL_MACHINE
strComputer = "."
strKey = "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\"
strEntry1a = "DisplayName"
strEntry1b = "QuietDisplayName"
Set objReg = GetObject("winmgmts:{impersonationLevel=impersonate}//" & _
strComputer & "/root/default:StdRegProv")
objReg.EnumKey HKLM, strKey, arrSubkeys
For Each strSubkey In arrSubkeys
intRet1 = objReg.GetStringValue(HKLM, strKey & strSubkey, _
strEntry1a, strValue1)
If intRet1 <> 0 Then
objReg.GetStringValue HKLM, strKey & strSubkey, _
strEntry1b, strValue1
End If
If strValue1 <> "" Then
cscript.echo strValue1
End If
Next