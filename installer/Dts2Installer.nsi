;NSIS Modern User Interface version 1.70
;Dts2 Installer Script
;Written by Stephen Strenn

;--------------------------------
;Include Modern UI

  !include "MUI.nsh"

;--------------------------------
;General

  ;Name and file
  Name "Dts2"
  OutFile "달구지2 Setup.exe"

  ;Default installation folder
  InstallDir "$PROGRAMFILES\casmall\Dts2"
  
  ;Get installation folder from registry if available
  InstallDirRegKey HKCU "Software\Dts2" ""

;--------------------------------
;Interface Settings

  !define MUI_ABORTWARNING
	!define MUI_HEADERIMAGE ".\Dts2InstallerSplash.bmp"
	!define MUI_HEADERIMAGE_BITMAP_NOSTRETCH
	!define MUI_HEADERIMAGE_BITMAP ".\Dts2InstallerSplash.bmp"
	!define MUI_ICON ".\setup.ico"
	!define MUI_UNICON ".\setup.ico"

;--------------------------------
;Pages

  !insertmacro MUI_PAGE_LICENSE ".\License.txt"
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES
  
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES
  
;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
;Installer Sections

Section "Dts2 (required)" SecDummy

  SectionIn RO

  ;Files to be installed
  SetOutPath "$INSTDIR"
  File ".\Dts2.ico"
	File /r ".\Dts2\"
	File /r ".\copy\"

    ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\Dts2 "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Dts2" "DisplayName" "Dts2"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Dts2" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Dts2" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Dts2" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
SectionEnd

; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"
  SetShellVarContext all
  CreateDirectory "$SMPROGRAMS\달구지2"
  CreateShortCut "$SMPROGRAMS\달구지2\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe"
  CreateShortCut "$SMPROGRAMS\달구지2\달구지2.lnk" "$INSTDIR\Dts2.exe" "" "$INSTDIR\Dts2.ico"
SectionEnd

;--------------------------------
;Uninstaller Section

Section "Uninstall"

  ; Remove registry keys
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Dts2"
  DeleteRegKey HKLM SOFTWARE\Dts2
  DeleteRegKey /ifempty HKCU "Software\Dts2"

	; Remove shortcuts
  SetShellVarContext all
  RMDir /r "$SMPROGRAMS\달구지2"

  ; Remove directories used
  RMDir /r "$INSTDIR"

SectionEnd