Project Path: chatbot

Source Tree:

```txt
chatbot
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── br
    │   │       └── com
    │   │           └── TrabalhoEngSoftware
    │   │               └── chatbot
    │   │                   ├── ChatbotApplication.java
    │   │                   ├── config
    │   │                   │   ├── Constants.java
    │   │                   │   └── JacksonConfig.java
    │   │                   ├── controller
    │   │                   │   ├── DeckAppController.java
    │   │                   │   └── FlashcardAppController.java
    │   │                   ├── dto
    │   │                   │   ├── AuthenticationDTO.java
    │   │                   │   ├── DeckDTO.java
    │   │                   │   ├── DeckSummaryDTO.java
    │   │                   │   ├── NoteSummaryDTO.java
    │   │                   │   ├── NoteUpdateDTO.java
    │   │                   │   ├── RegisterDTO.java
    │   │                   │   ├── SourceDTO.java
    │   │                   │   ├── StandardFlashcardDTO.java
    │   │                   │   ├── StandardUserAnswerDTO.java
    │   │                   │   ├── TokenDTO.java
    │   │                   │   ├── UpdatePasswordDTO.java
    │   │                   │   └── UserDTO.java
    │   │                   ├── entity
    │   │                   │   └── StandardFlashcardEntity.java
    │   │                   ├── handler
    │   │                   │   ├── StandardFlashcardHandler.java
    │   │                   │   └── StandardFlashcardSearchHandler.java
    │   │                   ├── repository
    │   │                   │   ├── FlashcardAppRepository.java
    │   │                   │   └── SourceRepository.java
    │   │                   ├── service
    │   │                   │   ├── DeckAppService.java
    │   │                   │   ├── FlashcardAppService.java
    │   │                   │   └── StandardFlashcardService.java
    │   │                   └── specification
    │   │                       ├── DeckSpecification.java
    │   │                       └── FlashcardSpecification.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── br
                └── com
                    └── TrabalhoEngSoftware
                        └── chatbot
                            └── ChatbotApplicationTests.java

```

`chatbot/HELP.md`:

```md
   1 | # Getting Started
   2 | 
   3 | ### Reference Documentation
   4 | For further reference, please consider the following sections:
   5 | 
   6 | * [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
   7 | * [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/maven-plugin)
   8 | * [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/maven-plugin/build-image.html)
   9 | * [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.4/reference/using/devtools.html)
  10 | * [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)
  11 | * [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.4/reference/data/sql.html#data.sql.jpa-and-spring-data)
  12 | 
  13 | ### Guides
  14 | The following guides illustrate how to use some features concretely:
  15 | 
  16 | * [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
  17 | * [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
  18 | * [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
  19 | * [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
  20 | 
  21 | ### Maven Parent overrides
  22 | 
  23 | Due to Maven's design, elements are inherited from the parent POM to the project POM.
  24 | While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
  25 | To prevent this, the project POM contains empty overrides for these elements.
  26 | If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
  27 | 

```

`chatbot/mvnw`:

```
   1 | #!/bin/sh
   2 | # ----------------------------------------------------------------------------
   3 | # Licensed to the Apache Software Foundation (ASF) under one
   4 | # or more contributor license agreements.  See the NOTICE file
   5 | # distributed with this work for additional information
   6 | # regarding copyright ownership.  The ASF licenses this file
   7 | # to you under the Apache License, Version 2.0 (the
   8 | # "License"); you may not use this file except in compliance
   9 | # with the License.  You may obtain a copy of the License at
  10 | #
  11 | #    http://www.apache.org/licenses/LICENSE-2.0
  12 | #
  13 | # Unless required by applicable law or agreed to in writing,
  14 | # software distributed under the License is distributed on an
  15 | # "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  16 | # KIND, either express or implied.  See the License for the
  17 | # specific language governing permissions and limitations
  18 | # under the License.
  19 | # ----------------------------------------------------------------------------
  20 | 
  21 | # ----------------------------------------------------------------------------
  22 | # Apache Maven Wrapper startup batch script, version 3.3.2
  23 | #
  24 | # Optional ENV vars
  25 | # -----------------
  26 | #   JAVA_HOME - location of a JDK home dir, required when download maven via java source
  27 | #   MVNW_REPOURL - repo url base for downloading maven distribution
  28 | #   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
  29 | #   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence the output
  30 | # ----------------------------------------------------------------------------
  31 | 
  32 | set -euf
  33 | [ "${MVNW_VERBOSE-}" != debug ] || set -x
  34 | 
  35 | # OS specific support.
  36 | native_path() { printf %s\\n "$1"; }
  37 | case "$(uname)" in
  38 | CYGWIN* | MINGW*)
  39 |   [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  40 |   native_path() { cygpath --path --windows "$1"; }
  41 |   ;;
  42 | esac
  43 | 
  44 | # set JAVACMD and JAVACCMD
  45 | set_java_home() {
  46 |   # For Cygwin and MinGW, ensure paths are in Unix format before anything is touched
  47 |   if [ -n "${JAVA_HOME-}" ]; then
  48 |     if [ -x "$JAVA_HOME/jre/sh/java" ]; then
  49 |       # IBM's JDK on AIX uses strange locations for the executables
  50 |       JAVACMD="$JAVA_HOME/jre/sh/java"
  51 |       JAVACCMD="$JAVA_HOME/jre/sh/javac"
  52 |     else
  53 |       JAVACMD="$JAVA_HOME/bin/java"
  54 |       JAVACCMD="$JAVA_HOME/bin/javac"
  55 | 
  56 |       if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
  57 |         echo "The JAVA_HOME environment variable is not defined correctly, so mvnw cannot run." >&2
  58 |         echo "JAVA_HOME is set to \"$JAVA_HOME\", but \"\$JAVA_HOME/bin/java\" or \"\$JAVA_HOME/bin/javac\" does not exist." >&2
  59 |         return 1
  60 |       fi
  61 |     fi
  62 |   else
  63 |     JAVACMD="$(
  64 |       'set' +e
  65 |       'unset' -f command 2>/dev/null
  66 |       'command' -v java
  67 |     )" || :
  68 |     JAVACCMD="$(
  69 |       'set' +e
  70 |       'unset' -f command 2>/dev/null
  71 |       'command' -v javac
  72 |     )" || :
  73 | 
  74 |     if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
  75 |       echo "The java/javac command does not exist in PATH nor is JAVA_HOME set, so mvnw cannot run." >&2
  76 |       return 1
  77 |     fi
  78 |   fi
  79 | }
  80 | 
  81 | # hash string like Java String::hashCode
  82 | hash_string() {
  83 |   str="${1:-}" h=0
  84 |   while [ -n "$str" ]; do
  85 |     char="${str%"${str#?}"}"
  86 |     h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
  87 |     str="${str#?}"
  88 |   done
  89 |   printf %x\\n $h
  90 | }
  91 | 
  92 | verbose() { :; }
  93 | [ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "${1-}"; }
  94 | 
  95 | die() {
  96 |   printf %s\\n "$1" >&2
  97 |   exit 1
  98 | }
  99 | 
 100 | trim() {
 101 |   # MWRAPPER-139:
 102 |   #   Trims trailing and leading whitespace, carriage returns, tabs, and linefeeds.
 103 |   #   Needed for removing poorly interpreted newline sequences when running in more
 104 |   #   exotic environments such as mingw bash on Windows.
 105 |   printf "%s" "${1}" | tr -d '[:space:]'
 106 | }
 107 | 
 108 | # parse distributionUrl and optional distributionSha256Sum, requires .mvn/wrapper/maven-wrapper.properties
 109 | while IFS="=" read -r key value; do
 110 |   case "${key-}" in
 111 |   distributionUrl) distributionUrl=$(trim "${value-}") ;;
 112 |   distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
 113 |   esac
 114 | done <"${0%/*}/.mvn/wrapper/maven-wrapper.properties"
 115 | [ -n "${distributionUrl-}" ] || die "cannot read distributionUrl property in ${0%/*}/.mvn/wrapper/maven-wrapper.properties"
 116 | 
 117 | case "${distributionUrl##*/}" in
 118 | maven-mvnd-*bin.*)
 119 |   MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/
 120 |   case "${PROCESSOR_ARCHITECTURE-}${PROCESSOR_ARCHITEW6432-}:$(uname -a)" in
 121 |   *AMD64:CYGWIN* | *AMD64:MINGW*) distributionPlatform=windows-amd64 ;;
 122 |   :Darwin*x86_64) distributionPlatform=darwin-amd64 ;;
 123 |   :Darwin*arm64) distributionPlatform=darwin-aarch64 ;;
 124 |   :Linux*x86_64*) distributionPlatform=linux-amd64 ;;
 125 |   *)
 126 |     echo "Cannot detect native platform for mvnd on $(uname)-$(uname -m), use pure java version" >&2
 127 |     distributionPlatform=linux-amd64
 128 |     ;;
 129 |   esac
 130 |   distributionUrl="${distributionUrl%-bin.*}-$distributionPlatform.zip"
 131 |   ;;
 132 | maven-mvnd-*) MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/ ;;
 133 | *) MVN_CMD="mvn${0##*/mvnw}" _MVNW_REPO_PATTERN=/org/apache/maven/ ;;
 134 | esac
 135 | 
 136 | # apply MVNW_REPOURL and calculate MAVEN_HOME
 137 | # maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
 138 | [ -z "${MVNW_REPOURL-}" ] || distributionUrl="$MVNW_REPOURL$_MVNW_REPO_PATTERN${distributionUrl#*"$_MVNW_REPO_PATTERN"}"
 139 | distributionUrlName="${distributionUrl##*/}"
 140 | distributionUrlNameMain="${distributionUrlName%.*}"
 141 | distributionUrlNameMain="${distributionUrlNameMain%-bin}"
 142 | MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
 143 | MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distributionUrlNameMain-}/$(hash_string "$distributionUrl")"
 144 | 
 145 | exec_maven() {
 146 |   unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
 147 |   exec "$MAVEN_HOME/bin/$MVN_CMD" "$@" || die "cannot exec $MAVEN_HOME/bin/$MVN_CMD"
 148 | }
 149 | 
 150 | if [ -d "$MAVEN_HOME" ]; then
 151 |   verbose "found existing MAVEN_HOME at $MAVEN_HOME"
 152 |   exec_maven "$@"
 153 | fi
 154 | 
 155 | case "${distributionUrl-}" in
 156 | *?-bin.zip | *?maven-mvnd-?*-?*.zip) ;;
 157 | *) die "distributionUrl is not valid, must match *-bin.zip or maven-mvnd-*.zip, but found '${distributionUrl-}'" ;;
 158 | esac
 159 | 
 160 | # prepare tmp dir
 161 | if TMP_DOWNLOAD_DIR="$(mktemp -d)" && [ -d "$TMP_DOWNLOAD_DIR" ]; then
 162 |   clean() { rm -rf -- "$TMP_DOWNLOAD_DIR"; }
 163 |   trap clean HUP INT TERM EXIT
 164 | else
 165 |   die "cannot create temp dir"
 166 | fi
 167 | 
 168 | mkdir -p -- "${MAVEN_HOME%/*}"
 169 | 
 170 | # Download and Install Apache Maven
 171 | verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
 172 | verbose "Downloading from: $distributionUrl"
 173 | verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"
 174 | 
 175 | # select .zip or .tar.gz
 176 | if ! command -v unzip >/dev/null; then
 177 |   distributionUrl="${distributionUrl%.zip}.tar.gz"
 178 |   distributionUrlName="${distributionUrl##*/}"
 179 | fi
 180 | 
 181 | # verbose opt
 182 | __MVNW_QUIET_WGET=--quiet __MVNW_QUIET_CURL=--silent __MVNW_QUIET_UNZIP=-q __MVNW_QUIET_TAR=''
 183 | [ "${MVNW_VERBOSE-}" != true ] || __MVNW_QUIET_WGET='' __MVNW_QUIET_CURL='' __MVNW_QUIET_UNZIP='' __MVNW_QUIET_TAR=v
 184 | 
 185 | # normalize http auth
 186 | case "${MVNW_PASSWORD:+has-password}" in
 187 | '') MVNW_USERNAME='' MVNW_PASSWORD='' ;;
 188 | has-password) [ -n "${MVNW_USERNAME-}" ] || MVNW_USERNAME='' MVNW_PASSWORD='' ;;
 189 | esac
 190 | 
 191 | if [ -z "${MVNW_USERNAME-}" ] && command -v wget >/dev/null; then
 192 |   verbose "Found wget ... using wget"
 193 |   wget ${__MVNW_QUIET_WGET:+"$__MVNW_QUIET_WGET"} "$distributionUrl" -O "$TMP_DOWNLOAD_DIR/$distributionUrlName" || die "wget: Failed to fetch $distributionUrl"
 194 | elif [ -z "${MVNW_USERNAME-}" ] && command -v curl >/dev/null; then
 195 |   verbose "Found curl ... using curl"
 196 |   curl ${__MVNW_QUIET_CURL:+"$__MVNW_QUIET_CURL"} -f -L -o "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" || die "curl: Failed to fetch $distributionUrl"
 197 | elif set_java_home; then
 198 |   verbose "Falling back to use Java to download"
 199 |   javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
 200 |   targetZip="$TMP_DOWNLOAD_DIR/$distributionUrlName"
 201 |   cat >"$javaSource" <<-END
 202 | 	public class Downloader extends java.net.Authenticator
 203 | 	{
 204 | 	  protected java.net.PasswordAuthentication getPasswordAuthentication()
 205 | 	  {
 206 | 	    return new java.net.PasswordAuthentication( System.getenv( "MVNW_USERNAME" ), System.getenv( "MVNW_PASSWORD" ).toCharArray() );
 207 | 	  }
 208 | 	  public static void main( String[] args ) throws Exception
 209 | 	  {
 210 | 	    setDefault( new Downloader() );
 211 | 	    java.nio.file.Files.copy( java.net.URI.create( args[0] ).toURL().openStream(), java.nio.file.Paths.get( args[1] ).toAbsolutePath().normalize() );
 212 | 	  }
 213 | 	}
 214 | 	END
 215 |   # For Cygwin/MinGW, switch paths to Windows format before running javac and java
 216 |   verbose " - Compiling Downloader.java ..."
 217 |   "$(native_path "$JAVACCMD")" "$(native_path "$javaSource")" || die "Failed to compile Downloader.java"
 218 |   verbose " - Running Downloader.java ..."
 219 |   "$(native_path "$JAVACMD")" -cp "$(native_path "$TMP_DOWNLOAD_DIR")" Downloader "$distributionUrl" "$(native_path "$targetZip")"
 220 | fi
 221 | 
 222 | # If specified, validate the SHA-256 sum of the Maven distribution zip file
 223 | if [ -n "${distributionSha256Sum-}" ]; then
 224 |   distributionSha256Result=false
 225 |   if [ "$MVN_CMD" = mvnd.sh ]; then
 226 |     echo "Checksum validation is not supported for maven-mvnd." >&2
 227 |     echo "Please disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
 228 |     exit 1
 229 |   elif command -v sha256sum >/dev/null; then
 230 |     if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | sha256sum -c >/dev/null 2>&1; then
 231 |       distributionSha256Result=true
 232 |     fi
 233 |   elif command -v shasum >/dev/null; then
 234 |     if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | shasum -a 256 -c >/dev/null 2>&1; then
 235 |       distributionSha256Result=true
 236 |     fi
 237 |   else
 238 |     echo "Checksum validation was requested but neither 'sha256sum' or 'shasum' are available." >&2
 239 |     echo "Please install either command, or disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
 240 |     exit 1
 241 |   fi
 242 |   if [ $distributionSha256Result = false ]; then
 243 |     echo "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised." >&2
 244 |     echo "If you updated your Maven version, you need to update the specified distributionSha256Sum property." >&2
 245 |     exit 1
 246 |   fi
 247 | fi
 248 | 
 249 | # unzip and move
 250 | if command -v unzip >/dev/null; then
 251 |   unzip ${__MVNW_QUIET_UNZIP:+"$__MVNW_QUIET_UNZIP"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "failed to unzip"
 252 | else
 253 |   tar xzf${__MVNW_QUIET_TAR:+"$__MVNW_QUIET_TAR"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -C "$TMP_DOWNLOAD_DIR" || die "failed to untar"
 254 | fi
 255 | printf %s\\n "$distributionUrl" >"$TMP_DOWNLOAD_DIR/$distributionUrlNameMain/mvnw.url"
 256 | mv -- "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" "$MAVEN_HOME" || [ -d "$MAVEN_HOME" ] || die "fail to move MAVEN_HOME"
 257 | 
 258 | clean || :
 259 | exec_maven "$@"

```

`chatbot/mvnw.cmd`:

```cmd
   1 | <# : batch portion
   2 | @REM ----------------------------------------------------------------------------
   3 | @REM Licensed to the Apache Software Foundation (ASF) under one
   4 | @REM or more contributor license agreements.  See the NOTICE file
   5 | @REM distributed with this work for additional information
   6 | @REM regarding copyright ownership.  The ASF licenses this file
   7 | @REM to you under the Apache License, Version 2.0 (the
   8 | @REM "License"); you may not use this file except in compliance
   9 | @REM with the License.  You may obtain a copy of the License at
  10 | @REM
  11 | @REM    http://www.apache.org/licenses/LICENSE-2.0
  12 | @REM
  13 | @REM Unless required by applicable law or agreed to in writing,
  14 | @REM software distributed under the License is distributed on an
  15 | @REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  16 | @REM KIND, either express or implied.  See the License for the
  17 | @REM specific language governing permissions and limitations
  18 | @REM under the License.
  19 | @REM ----------------------------------------------------------------------------
  20 | 
  21 | @REM ----------------------------------------------------------------------------
  22 | @REM Apache Maven Wrapper startup batch script, version 3.3.2
  23 | @REM
  24 | @REM Optional ENV vars
  25 | @REM   MVNW_REPOURL - repo url base for downloading maven distribution
  26 | @REM   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
  27 | @REM   MVNW_VERBOSE - true: enable verbose log; others: silence the output
  28 | @REM ----------------------------------------------------------------------------
  29 | 
  30 | @IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
  31 | @SET __MVNW_CMD__=
  32 | @SET __MVNW_ERROR__=
  33 | @SET __MVNW_PSMODULEP_SAVE=%PSModulePath%
  34 | @SET PSModulePath=
  35 | @FOR /F "usebackq tokens=1* delims==" %%A IN (`powershell -noprofile "& {$scriptDir='%~dp0'; $script='%__MVNW_ARG0_NAME__%'; icm -ScriptBlock ([Scriptblock]::Create((Get-Content -Raw '%~f0'))) -NoNewScope}"`) DO @(
  36 |   IF "%%A"=="MVN_CMD" (set __MVNW_CMD__=%%B) ELSE IF "%%B"=="" (echo %%A) ELSE (echo %%A=%%B)
  37 | )
  38 | @SET PSModulePath=%__MVNW_PSMODULEP_SAVE%
  39 | @SET __MVNW_PSMODULEP_SAVE=
  40 | @SET __MVNW_ARG0_NAME__=
  41 | @SET MVNW_USERNAME=
  42 | @SET MVNW_PASSWORD=
  43 | @IF NOT "%__MVNW_CMD__%"=="" (%__MVNW_CMD__% %*)
  44 | @echo Cannot start maven from wrapper >&2 && exit /b 1
  45 | @GOTO :EOF
  46 | : end batch / begin powershell #>
  47 | 
  48 | $ErrorActionPreference = "Stop"
  49 | if ($env:MVNW_VERBOSE -eq "true") {
  50 |   $VerbosePreference = "Continue"
  51 | }
  52 | 
  53 | # calculate distributionUrl, requires .mvn/wrapper/maven-wrapper.properties
  54 | $distributionUrl = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionUrl
  55 | if (!$distributionUrl) {
  56 |   Write-Error "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"
  57 | }
  58 | 
  59 | switch -wildcard -casesensitive ( $($distributionUrl -replace '^.*/','') ) {
  60 |   "maven-mvnd-*" {
  61 |     $USE_MVND = $true
  62 |     $distributionUrl = $distributionUrl -replace '-bin\.[^.]*$',"-windows-amd64.zip"
  63 |     $MVN_CMD = "mvnd.cmd"
  64 |     break
  65 |   }
  66 |   default {
  67 |     $USE_MVND = $false
  68 |     $MVN_CMD = $script -replace '^mvnw','mvn'
  69 |     break
  70 |   }
  71 | }
  72 | 
  73 | # apply MVNW_REPOURL and calculate MAVEN_HOME
  74 | # maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
  75 | if ($env:MVNW_REPOURL) {
  76 |   $MVNW_REPO_PATTERN = if ($USE_MVND) { "/org/apache/maven/" } else { "/maven/mvnd/" }
  77 |   $distributionUrl = "$env:MVNW_REPOURL$MVNW_REPO_PATTERN$($distributionUrl -replace '^.*'+$MVNW_REPO_PATTERN,'')"
  78 | }
  79 | $distributionUrlName = $distributionUrl -replace '^.*/',''
  80 | $distributionUrlNameMain = $distributionUrlName -replace '\.[^.]*$','' -replace '-bin$',''
  81 | $MAVEN_HOME_PARENT = "$HOME/.m2/wrapper/dists/$distributionUrlNameMain"
  82 | if ($env:MAVEN_USER_HOME) {
  83 |   $MAVEN_HOME_PARENT = "$env:MAVEN_USER_HOME/wrapper/dists/$distributionUrlNameMain"
  84 | }
  85 | $MAVEN_HOME_NAME = ([System.Security.Cryptography.MD5]::Create().ComputeHash([byte[]][char[]]$distributionUrl) | ForEach-Object {$_.ToString("x2")}) -join ''
  86 | $MAVEN_HOME = "$MAVEN_HOME_PARENT/$MAVEN_HOME_NAME"
  87 | 
  88 | if (Test-Path -Path "$MAVEN_HOME" -PathType Container) {
  89 |   Write-Verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  90 |   Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
  91 |   exit $?
  92 | }
  93 | 
  94 | if (! $distributionUrlNameMain -or ($distributionUrlName -eq $distributionUrlNameMain)) {
  95 |   Write-Error "distributionUrl is not valid, must end with *-bin.zip, but found $distributionUrl"
  96 | }
  97 | 
  98 | # prepare tmp dir
  99 | $TMP_DOWNLOAD_DIR_HOLDER = New-TemporaryFile
 100 | $TMP_DOWNLOAD_DIR = New-Item -Itemtype Directory -Path "$TMP_DOWNLOAD_DIR_HOLDER.dir"
 101 | $TMP_DOWNLOAD_DIR_HOLDER.Delete() | Out-Null
 102 | trap {
 103 |   if ($TMP_DOWNLOAD_DIR.Exists) {
 104 |     try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
 105 |     catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
 106 |   }
 107 | }
 108 | 
 109 | New-Item -Itemtype Directory -Path "$MAVEN_HOME_PARENT" -Force | Out-Null
 110 | 
 111 | # Download and Install Apache Maven
 112 | Write-Verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
 113 | Write-Verbose "Downloading from: $distributionUrl"
 114 | Write-Verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"
 115 | 
 116 | $webclient = New-Object System.Net.WebClient
 117 | if ($env:MVNW_USERNAME -and $env:MVNW_PASSWORD) {
 118 |   $webclient.Credentials = New-Object System.Net.NetworkCredential($env:MVNW_USERNAME, $env:MVNW_PASSWORD)
 119 | }
 120 | [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
 121 | $webclient.DownloadFile($distributionUrl, "$TMP_DOWNLOAD_DIR/$distributionUrlName") | Out-Null
 122 | 
 123 | # If specified, validate the SHA-256 sum of the Maven distribution zip file
 124 | $distributionSha256Sum = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionSha256Sum
 125 | if ($distributionSha256Sum) {
 126 |   if ($USE_MVND) {
 127 |     Write-Error "Checksum validation is not supported for maven-mvnd. `nPlease disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties."
 128 |   }
 129 |   Import-Module $PSHOME\Modules\Microsoft.PowerShell.Utility -Function Get-FileHash
 130 |   if ((Get-FileHash "$TMP_DOWNLOAD_DIR/$distributionUrlName" -Algorithm SHA256).Hash.ToLower() -ne $distributionSha256Sum) {
 131 |     Write-Error "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised. If you updated your Maven version, you need to update the specified distributionSha256Sum property."
 132 |   }
 133 | }
 134 | 
 135 | # unzip and move
 136 | Expand-Archive "$TMP_DOWNLOAD_DIR/$distributionUrlName" -DestinationPath "$TMP_DOWNLOAD_DIR" | Out-Null
 137 | Rename-Item -Path "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" -NewName $MAVEN_HOME_NAME | Out-Null
 138 | try {
 139 |   Move-Item -Path "$TMP_DOWNLOAD_DIR/$MAVEN_HOME_NAME" -Destination $MAVEN_HOME_PARENT | Out-Null
 140 | } catch {
 141 |   if (! (Test-Path -Path "$MAVEN_HOME" -PathType Container)) {
 142 |     Write-Error "fail to move MAVEN_HOME"
 143 |   }
 144 | } finally {
 145 |   try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
 146 |   catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
 147 | }
 148 | 
 149 | Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"

```

`chatbot/pom.xml`:

```xml
   1 | <?xml version="1.0" encoding="UTF-8"?>
   2 | <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   3 | 	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   4 | 	<modelVersion>4.0.0</modelVersion>
   5 | 	<parent>
   6 | 		<groupId>org.springframework.boot</groupId>
   7 | 		<artifactId>spring-boot-starter-parent</artifactId>
   8 | 		<version>3.4.4</version>
   9 | 		<relativePath/> <!-- lookup parent from repository -->
  10 | 	</parent>
  11 | 	<groupId>br.com.TrabalhoEngSoftware</groupId>
  12 | 	<artifactId>chatbot</artifactId>
  13 | 	<version>0.0.1-SNAPSHOT</version>
  14 | 	<name>chatbot</name>
  15 | 	<description>Projeto da disciplina de Engenharia de Software</description>
  16 | 	<url/>
  17 | 	<licenses>
  18 | 		<license/>
  19 | 	</licenses>
  20 | 	<developers>
  21 | 		<developer/>
  22 | 	</developers>
  23 | 	<scm>
  24 | 		<connection/>
  25 | 		<developerConnection/>
  26 | 		<tag/>
  27 | 		<url/>
  28 | 	</scm>
  29 | 	<properties>
  30 | 		<java.version>17</java.version>
  31 | 	</properties>
  32 | 
  33 | <dependencyManagement>
  34 |   <dependencies>
  35 |     <dependency>
  36 |       <groupId>org.springframework.ai</groupId>
  37 |       <artifactId>spring-ai-bom</artifactId>
  38 |       <version>1.0.0-M8</version>
  39 |       <type>pom</type>
  40 |       <scope>import</scope>
  41 |     </dependency>
  42 |   </dependencies>
  43 | </dependencyManagement>
  44 | 
  45 | 	<dependencies>
  46 | 		<dependency>
  47 | 			<groupId>org.springframework.boot</groupId>
  48 | 			<artifactId>spring-boot-starter-data-jpa</artifactId>
  49 | 		</dependency>
  50 | 		<dependency>
  51 | 			<groupId>org.springframework.boot</groupId>
  52 | 			<artifactId>spring-boot-starter-web</artifactId>
  53 | 		</dependency>
  54 | 		<dependency>
  55 |     		<groupId>jakarta.validation</groupId>
  56 |     		<artifactId>jakarta.validation-api</artifactId>
  57 |     		<version>3.0.2</version> <!-- ou a mais recente -->
  58 | 		</dependency>
  59 | 		<dependency>
  60 | 			<groupId>org.springframework.boot</groupId>
  61 | 			<artifactId>spring-boot-devtools</artifactId>
  62 | 			<scope>runtime</scope>
  63 | 			<optional>true</optional>
  64 | 		</dependency>
  65 | 		<dependency>
  66 | 			<groupId>org.postgresql</groupId>
  67 | 			<artifactId>postgresql</artifactId>
  68 | 			<scope>runtime</scope>
  69 | 		</dependency>
  70 | 		<dependency>
  71 | 			<groupId>org.springframework.boot</groupId>
  72 | 			<artifactId>spring-boot-starter-test</artifactId>
  73 | 			<scope>test</scope>
  74 | 		</dependency>
  75 | 		<dependency>
  76 |       		<groupId>org.springframework.boot</groupId>
  77 |       		<artifactId>spring-boot-starter-security</artifactId>
  78 |     	</dependency>
  79 |     	<dependency>
  80 |       		<groupId>org.springframework.security</groupId>
  81 |       		<artifactId>spring-security-test</artifactId>
  82 |       		<scope>test</scope>
  83 |     	</dependency>
  84 |     	<dependency>
  85 | 			<groupId>com.auth0</groupId>
  86 |     		<artifactId>java-jwt</artifactId>
  87 |     		<version>4.4.0</version>
  88 |     	</dependency>
  89 | 		<dependency>
  90 | 			<groupId>org.springframework.ai</groupId>
  91 | 			<artifactId>spring-ai-starter-model-openai</artifactId>
  92 | 		</dependency>
  93 | 		<dependency>
  94 |     		<groupId>org.apache.tika</groupId>
  95 | 			<artifactId>tika-core</artifactId>
  96 | 			<version>2.9.1</version> 
  97 | 		</dependency>
  98 | 		<dependency>
  99 | 			<groupId>org.apache.tika</groupId>
 100 | 			<artifactId>tika-parsers-standard-package</artifactId>
 101 | 			<version>2.9.1</version> 
 102 | 		</dependency>
 103 | 		<dependency>
 104 | 		  <groupId>br.com.TrabalhoEngSoftwareFramework</groupId>
 105 | 		  <artifactId>framework</artifactId>
 106 | 		  <version>0.0.1-SNAPSHOT</version>
 107 | 		  <exclusions>
 108 | 		    <exclusion>
 109 | 		      <groupId>com.vaadin.external.google</groupId>
 110 | 		      <artifactId>android-json</artifactId>
 111 | 		    </exclusion>
 112 | 		    <exclusion>
 113 | 		      <groupId>org.springframework.ai</groupId>
 114 | 		      <artifactId>spring-ai-autoconfigure</artifactId>
 115 | 		    </exclusion>
 116 | 		    <exclusion>
 117 | 		      <groupId>org.springframework.ai</groupId>
 118 | 		      <artifactId>spring-ai-core</artifactId>
 119 | 		    </exclusion>
 120 | 		    </exclusions>
 121 | 		</dependency>
 122 | 	
 123 |    <dependency>
 124 |      <groupId>io.komune.f2</groupId>
 125 |      <artifactId>f2-spring-boot-starter-function</artifactId>
 126 |      <version>0.21.0</version>
 127 |    </dependency>
 128 |  </dependencies>
 129 | 
 130 | 	<build>
 131 | 		<plugins>
 132 | 			<plugin>
 133 | 				<groupId>org.springframework.boot</groupId>
 134 | 				<artifactId>spring-boot-maven-plugin</artifactId>
 135 | 			</plugin>
 136 | 		</plugins>
 137 | 	</build>
 138 | 
 139 | </project>

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/ChatbotApplication.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot;
   2 | 
   3 | import org.springframework.boot.SpringApplication;
   4 | import org.springframework.boot.autoconfigure.SpringBootApplication;
   5 | import org.springframework.boot.autoconfigure.domain.EntityScan;
   6 | import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
   7 | import org.springframework.context.annotation.ComponentScan;
   8 | import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
   9 | import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
  10 | 
  11 | @EnableJpaAuditing
  12 | @SpringBootApplication(exclude = { ContextFunctionCatalogAutoConfiguration.class })
  13 | @EntityScan(basePackages = "br.com.TrabalhoEngSoftwareFramework.framework.entity")
  14 | @EnableJpaRepositories(basePackages = "br.com.TrabalhoEngSoftwareFramework.framework.repository")
  15 | @ComponentScan(basePackages = {
  16 |     "br.com.TrabalhoEngSoftwareFramework.framework",  // <- escaneia serviços como TopicService
  17 |     "br.com.TrabalhoEngSoftware.chatbot"              // <- seu código local
  18 | })
  19 | public class ChatbotApplication {
  20 | 
  21 | 	public static void main(String[] args) {
  22 | 		SpringApplication.run(ChatbotApplication.class, args);
  23 | 	}
  24 | 
  25 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/config/Constants.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.config;
   2 | 
   3 | public class Constants {
   4 | 
   5 |   public static int WRONG = 0;
   6 |   public static int HARD = 2;
   7 |   public static int GOOD = 4;
   8 |   public static int EASY = 5;
   9 | 
  10 |   public static double MIN_EASE_FACTOR = 1.3;
  11 | 
  12 |   private Constants() {
  13 |     
  14 |   }
  15 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/config/JacksonConfig.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.config;
   2 | 
   3 | import org.springframework.context.annotation.Bean;
   4 | import org.springframework.context.annotation.Configuration;
   5 | 
   6 | import com.fasterxml.jackson.databind.ObjectMapper;
   7 | import com.fasterxml.jackson.databind.jsontype.NamedType;
   8 | import com.fasterxml.jackson.databind.module.SimpleModule;
   9 | 
  10 | import br.com.TrabalhoEngSoftware.chatbot.dto.StandardFlashcardDTO;
  11 | import br.com.TrabalhoEngSoftware.chatbot.dto.StandardUserAnswerDTO;
  12 | 
  13 | @Configuration
  14 | public class JacksonConfig {
  15 | 
  16 |   @Bean
  17 |   public ObjectMapper objectMapper() {
  18 |     ObjectMapper objectMapper = new ObjectMapper();
  19 |     SimpleModule module = new SimpleModule();
  20 | 
  21 |     module.registerSubtypes(
  22 |       new NamedType(StandardFlashcardDTO.class, "STANDARD_FLASHCARD")
  23 |     );
  24 | 
  25 |     module.registerSubtypes(
  26 |       new NamedType(StandardUserAnswerDTO.class, "STANDARD_USER_ANSWER")
  27 |     );
  28 | 
  29 |     objectMapper.registerModule(module);
  30 |     return objectMapper;
  31 |   }
  32 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/controller/DeckAppController.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.controller;
   2 | 
   3 | import org.springframework.beans.factory.annotation.Autowired;
   4 | import org.springframework.security.core.Authentication;
   5 | import org.springframework.web.bind.annotation.GetMapping;
   6 | import org.springframework.web.bind.annotation.PathVariable;
   7 | import org.springframework.web.bind.annotation.RequestMapping;
   8 | import org.springframework.web.bind.annotation.RestController;
   9 | 
  10 | import br.com.TrabalhoEngSoftware.chatbot.service.DeckAppService;
  11 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.UserEntity;
  12 | 
  13 | @RestController
  14 | @RequestMapping("api/decks")
  15 | public class DeckAppController {
  16 | 
  17 |   @Autowired
  18 |   private DeckAppService deckService;
  19 | 
  20 |   @GetMapping("/get-due-flashcards-total/{deckId}")
  21 |   public long getDueFlashcardsTotal(@PathVariable Long deckId, Authentication authentication) {
  22 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  23 |     return deckService.getDueFlashcardsTotal(deckId, user.getId());
  24 |   }
  25 | 
  26 |   @GetMapping("/get-mastery-level/{deckId}")
  27 |   public double getMasteryLevel(@PathVariable Long deckId, Authentication authentication) {
  28 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  29 |     return deckService.getMasteryLevel(deckId, user.getId());
  30 |   }
  31 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/controller/FlashcardAppController.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.controller;
   2 | 
   3 | import org.springframework.beans.factory.annotation.Autowired;
   4 | import org.springframework.http.ResponseEntity;
   5 | import org.springframework.security.core.Authentication;
   6 | import org.springframework.web.bind.annotation.GetMapping;
   7 | import org.springframework.web.bind.annotation.PathVariable;
   8 | import org.springframework.web.bind.annotation.PutMapping;
   9 | import org.springframework.web.bind.annotation.RequestBody;
  10 | import org.springframework.web.bind.annotation.RequestMapping;
  11 | import org.springframework.web.bind.annotation.RestController;
  12 | 
  13 | import br.com.TrabalhoEngSoftware.chatbot.service.FlashcardAppService;
  14 | import br.com.TrabalhoEngSoftwareFramework.framework.controller.FlashcardController;
  15 | import br.com.TrabalhoEngSoftwareFramework.framework.dto.FlashcardDTO;
  16 | import br.com.TrabalhoEngSoftwareFramework.framework.dto.UserAnswerDTO;
  17 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.UserEntity;
  18 | 
  19 | @RestController
  20 | @RequestMapping("api/flashcards")
  21 | public class FlashcardAppController extends FlashcardController {
  22 | 
  23 |   @Autowired
  24 |   private FlashcardAppService flashcardService;
  25 | 
  26 |   public static class GenerateFlashcardsRequest {
  27 |     private int count = 5; // Default count
  28 | 
  29 |     public int getCount() {
  30 |       return count;
  31 |     }
  32 | 
  33 |     public void setCount(int count) {
  34 |       this.count = count;
  35 |     }
  36 |   }
  37 | 
  38 |   @GetMapping("/next-due-flashcard-by-deck-id/{deckId}")
  39 |   public ResponseEntity<FlashcardDTO> getNextDueFlashcardByDeckId(@PathVariable Long deckId, Authentication authentication) {
  40 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  41 |     return flashcardService.getNextDueFlashcardByDeckId(deckId, user.getId())
  42 |            .map(ResponseEntity::ok)
  43 |            .orElseGet(() -> ResponseEntity.noContent().build());
  44 |   }
  45 | 
  46 |   @PutMapping("/apply-review-result/{flashcardId}")
  47 |   public void applyReviewResult(@PathVariable Long flashcardId, @RequestBody UserAnswerDTO answer, Authentication authentication) {
  48 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  49 |     flashcardService.applyReviewResult(flashcardId, answer, user.getId());
  50 |   }
  51 | 
  52 |   @GetMapping("/get-count-new-flashcards/{deckId}")
  53 |   public long getCountNewFlashcards(@PathVariable Long deckId, Authentication authentication) {
  54 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  55 |     return flashcardService.getCountNewFlashcards(deckId, user.getId());
  56 |   }
  57 | 
  58 |   @GetMapping("/get-count-learning-flashcards/{deckId}")
  59 |   public long getCountLearningFlashcards(@PathVariable Long deckId, Authentication authentication) {
  60 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  61 |     return flashcardService.getCountLearningFlashcards(deckId, user.getId());
  62 |   }
  63 | 
  64 |   @GetMapping("/get-count-review-flashcards/{deckId}")
  65 |   public long getCountReviewFlashcards(@PathVariable Long deckId, Authentication authentication) {
  66 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  67 |     return flashcardService.getCountReviewFlashcards(deckId, user.getId());
  68 |   }
  69 | 
  70 |   @GetMapping("/next-due-flashcard")
  71 |   public ResponseEntity<FlashcardDTO> getNextDueFlashcard(Authentication authentication) {
  72 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  73 |     return flashcardService.getNextDueFlashcard(user.getId())
  74 |            .map(ResponseEntity::ok)
  75 |            .orElseGet(() -> ResponseEntity.noContent().build());
  76 |   }
  77 | 
  78 |   @GetMapping("/get-count-all-new-flashcards")
  79 |   public long getCountAllNewFlashcards(Authentication authentication) {
  80 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  81 |     return flashcardService.getCountAllNewFlashcards(user.getId());
  82 |   }
  83 | 
  84 |   @GetMapping("/get-count-all-learning-flashcards")
  85 |   public long getCountAllLearningFlashcards(Authentication authentication) {
  86 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  87 |     return flashcardService.getCountAllLearningFlashcards(user.getId());
  88 |   }
  89 | 
  90 |   @GetMapping("/get-count-all-review-flashcards")
  91 |   public long getCountAllReviewFlashcards(Authentication authentication) {
  92 |     UserEntity user = (UserEntity) authentication.getPrincipal();
  93 |     return flashcardService.getCountAllReviewFlashcards(user.getId());
  94 |   }
  95 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/AuthenticationDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | public record AuthenticationDTO(String email, String password) {
   4 | 	
   5 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/DeckDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | import java.util.ArrayList;
   5 | import java.util.List;
   6 | 
   7 | import org.springframework.beans.BeanUtils;
   8 | 
   9 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
  10 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
  11 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.UserEntity;
  12 | 
  13 | public class DeckDTO {
  14 | 	
  15 | 	private Long id;
  16 | 	private String title;
  17 | 	private String topic;
  18 | 	private LocalDateTime createdAt;
  19 | 	private LocalDateTime lastReviewedAt;
  20 | 	private UserEntity userEntity;
  21 | 	private List<FlashcardEntity> flashcards = new ArrayList<>();
  22 | 	
  23 | 	public DeckDTO(DeckEntity deck) {
  24 | 		BeanUtils.copyProperties(deck, this);
  25 | 	}
  26 | 	
  27 | 	public DeckDTO() {
  28 | 		
  29 | 	}
  30 | 
  31 | 	public Long getId() {
  32 | 		return id;
  33 | 	}
  34 | 
  35 | 	public void setId(Long id) {
  36 | 		this.id = id;
  37 | 	}
  38 | 
  39 | 	public String getTitle() {
  40 | 		return title;
  41 | 	}
  42 | 
  43 | 	public void setTitle(String title) {
  44 | 		this.title = title;
  45 | 	}
  46 | 
  47 | 	public String getTopic() {
  48 | 		return topic;
  49 | 	}
  50 | 
  51 | 	public void setTopic(String topic) {
  52 | 		this.topic = topic;
  53 | 	}
  54 | 
  55 | 	public LocalDateTime getCreatedAt() {
  56 | 		return createdAt;
  57 | 	}
  58 | 
  59 | 	public void setCreatedAt(LocalDateTime createdAt) {
  60 | 		this.createdAt = createdAt;
  61 | 	}
  62 | 
  63 | 	public LocalDateTime getLastReviewedAt() {
  64 | 		return lastReviewedAt;
  65 | 	}
  66 | 
  67 | 	public void setLastReviewedAt(LocalDateTime lastReviewedAt) {
  68 | 		this.lastReviewedAt = lastReviewedAt;
  69 | 	}
  70 | 
  71 | 	public UserEntity getUserEntity() {
  72 | 		return userEntity;
  73 | 	}
  74 | 
  75 | 	public void setUserEntity(UserEntity userEntity) {
  76 | 		this.userEntity = userEntity;
  77 | 	}
  78 | 
  79 | 	public List<FlashcardEntity> getFlashcards() {
  80 | 		return flashcards;
  81 | 	}
  82 | 
  83 | 	public void setFlashcards(List<FlashcardEntity> flashcards) {
  84 | 		this.flashcards = flashcards;
  85 | 	}
  86 | 	
  87 | 	
  88 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/DeckSummaryDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
   6 | 
   7 | public class DeckSummaryDTO {
   8 | 
   9 |   private Long id;
  10 | 	private String title;
  11 | 	private String topic;
  12 | 	private LocalDateTime createdAt;
  13 | 	private LocalDateTime lastReviewedAt;
  14 | 
  15 |   public DeckSummaryDTO(DeckEntity deck) {
  16 |     this.id = deck.getId();
  17 |     this.title = deck.getTitle();
  18 |     this.topic = deck.getTopic();
  19 |     this.createdAt = deck.getCreatedAt();
  20 |     this.lastReviewedAt = deck.getLastReviewedAt();
  21 |   }
  22 | 
  23 |   public DeckSummaryDTO() {
  24 |     
  25 |   }
  26 | 
  27 |   public Long getId() {
  28 |     return id;
  29 |   }
  30 | 
  31 |   public void setId(Long id) {
  32 |     this.id = id;
  33 |   }
  34 | 
  35 |   public String getTitle() {
  36 |     return title;
  37 |   }
  38 | 
  39 |   public void setTitle(String title) {
  40 |     this.title = title;
  41 |   }
  42 | 
  43 |   public String getTopic() {
  44 |     return topic;
  45 |   }
  46 | 
  47 |   public void setTopic(String topic) {
  48 |     this.topic = topic;
  49 |   }
  50 | 
  51 |   public LocalDateTime getCreatedAt() {
  52 |     return createdAt;
  53 |   }
  54 | 
  55 |   public void setCreatedAt(LocalDateTime createdAt) {
  56 |     this.createdAt = createdAt;
  57 |   }
  58 | 
  59 |   public LocalDateTime getLastReviewedAt() {
  60 |     return lastReviewedAt;
  61 |   }
  62 | 
  63 |   public void setLastReviewedAt(LocalDateTime lastReviewedAt) {
  64 |     this.lastReviewedAt = lastReviewedAt;
  65 |   }
  66 |   
  67 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/NoteSummaryDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.NoteEntity;
   6 | 
   7 | public class NoteSummaryDTO {
   8 | 	private Long id;
   9 | 	private String title;
  10 |   private String subtitle;
  11 |   private LocalDateTime updatedAt;
  12 |   private LocalDateTime createdAt;
  13 | 
  14 |   public NoteSummaryDTO(NoteEntity entity) {
  15 |   	this.id = entity.getId();
  16 |     this.title = entity.getTitle();
  17 |     this.subtitle = entity.getSubtitle();
  18 |     this.updatedAt = entity.getUpdatedAt();
  19 |     this.createdAt = entity.getCreatedAt();
  20 |   }
  21 | 
  22 | 	public Long getId() {
  23 | 		return id;
  24 | 	}
  25 | 
  26 | 	public void setId(Long id) {
  27 | 		this.id = id;
  28 | 	}
  29 | 
  30 | 	public String getTitle() {
  31 | 		return title;
  32 | 	}
  33 | 
  34 | 	public void setTitle(String title) {
  35 | 		this.title = title;
  36 | 	}
  37 | 
  38 | 	public String getSubtitle() {
  39 | 		return subtitle;
  40 | 	}
  41 | 
  42 | 	public void setSubtitle(String subtitle) {
  43 | 		this.subtitle = subtitle;
  44 | 	}
  45 | 
  46 | 	public LocalDateTime getUpdatedAt() {
  47 | 		return updatedAt;
  48 | 	}
  49 | 
  50 | 	public void setUpdatedAt(LocalDateTime updatedAt) {
  51 | 		this.updatedAt = updatedAt;
  52 | 	}
  53 | 
  54 | 	public LocalDateTime getCreatedAt() {
  55 | 		return createdAt;
  56 | 	}
  57 | 
  58 | 	public void setCreatedAt(LocalDateTime createdAt) {
  59 | 		this.createdAt = createdAt;
  60 | 	}
  61 |     
  62 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/NoteUpdateDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | public class NoteUpdateDTO {
   4 | 	private String title;
   5 |   private String content;
   6 |   private String subtitle;
   7 |     
   8 | 	public String getTitle() {
   9 | 		return title;
  10 | 	}
  11 | 	public void setTitle(String title) {
  12 | 		this.title = title;
  13 | 	}
  14 | 	public String getContent() {
  15 | 		return content;
  16 | 	}
  17 | 	public void setContent(String content) {
  18 | 		this.content = content;
  19 | 	}
  20 | 	public String getSubtitle() {
  21 | 		return subtitle;
  22 | 	}
  23 | 	public void setSubtitle(String subtitle) {
  24 | 		this.subtitle = subtitle;
  25 | 	}
  26 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/RegisterDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | public record RegisterDTO(String email, String fullName, String password) {
   4 | 
   5 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/SourceDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.SourceEntity;
   6 | 
   7 | public class SourceDTO {
   8 | 
   9 |     private Long id;
  10 |     private String fileName;
  11 |     //private String filePath;
  12 |     private LocalDateTime createdAt;
  13 | 
  14 |     // Constructor from Entity
  15 |     public SourceDTO(SourceEntity source) {
  16 |         this.id = source.getId();
  17 |         this.fileName = source.getFileName();
  18 |         // this.filePath = source.getFilePath(); // Considerar se você quer expor o caminho completo ou uma URL para download
  19 |         this.createdAt = source.getCreatedAt();
  20 |     }
  21 | 
  22 |     // Getters
  23 |     public Long getId() {
  24 |         return id;
  25 |     }
  26 | 
  27 |     public String getFileName() {
  28 |         return fileName;
  29 |     }
  30 | 
  31 |     /* public String getFilePath() {
  32 |         return filePath;
  33 |     } */
  34 | 
  35 |     public LocalDateTime getCreatedAt() {
  36 |         return createdAt;
  37 |     }
  38 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/StandardFlashcardDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.dto.FlashcardDTO;
   6 | 
   7 | public class StandardFlashcardDTO extends FlashcardDTO {
   8 | 	
   9 | 	private String front;
  10 | 	private String back;
  11 | 	private LocalDateTime nextReview;
  12 | 	private int repetition;
  13 | 	private double easeFactor;
  14 | 	private int interval;
  15 | 	
  16 | 	public StandardFlashcardDTO() {
  17 | 		super();
  18 | 		super.setFlashcardType("STANDARD_FLASHCARD");
  19 | 	}
  20 | 
  21 | 	public String getFront() {
  22 | 		return front;
  23 | 	}
  24 | 
  25 | 	public void setFront(String front) {
  26 | 		this.front = front;
  27 | 	}
  28 | 
  29 | 	public String getBack() {
  30 | 		return back;
  31 | 	}
  32 | 
  33 | 	public void setBack(String back) {
  34 | 		this.back = back;
  35 | 	}
  36 | 
  37 | 	public LocalDateTime getNextReview() {
  38 | 		return nextReview;
  39 | 	}
  40 | 
  41 | 	public void setNextReview(LocalDateTime nextReview) {
  42 | 		this.nextReview = nextReview;
  43 | 	}
  44 | 
  45 | 	public int getRepetition() {
  46 | 		return repetition;
  47 | 	}
  48 | 
  49 | 	public void setRepetition(int repetition) {
  50 | 		this.repetition = repetition;
  51 | 	}
  52 | 
  53 | 	public double getEaseFactor() {
  54 | 		return easeFactor;
  55 | 	}
  56 | 
  57 | 	public void setEaseFactor(double easeFactor) {
  58 | 		this.easeFactor = easeFactor;
  59 | 	}
  60 | 
  61 | 	public int getInterval() {
  62 | 		return interval;
  63 | 	}
  64 | 
  65 | 	public void setInterval(int interval) {
  66 | 		this.interval = interval;
  67 | 	}
  68 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/StandardUserAnswerDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import br.com.TrabalhoEngSoftwareFramework.framework.dto.UserAnswerDTO;
   4 | 
   5 | public class StandardUserAnswerDTO extends UserAnswerDTO {
   6 |   
   7 |   private int answer;
   8 | 
   9 |   public StandardUserAnswerDTO() {
  10 |     super();
  11 |   }
  12 | 
  13 |   public StandardUserAnswerDTO(Long flashcardId, String flashcardType, int answer) {
  14 |     super(flashcardId, flashcardType);
  15 |     this.answer = answer;
  16 |   }
  17 | 
  18 |   public int getAnswer() {
  19 |     return answer;
  20 |   }
  21 | 
  22 |   public void setAnswer(int answer) {
  23 |     this.answer = answer;
  24 |   }
  25 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/TokenDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | public record TokenDTO(String token) {
   4 | 
   5 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/UpdatePasswordDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | public class UpdatePasswordDTO {
   4 |   private String currentPassword;
   5 |   private String newPassword;
   6 |   private String confirmNewPassword;
   7 | 
   8 |   public String getCurrentPassword() {
   9 |     return currentPassword;
  10 |   }
  11 |   
  12 |   public void setCurrentPassword(String currentPassword) {
  13 |     this.currentPassword = currentPassword;
  14 |   }
  15 |   
  16 |   public String getNewPassword() {
  17 |     return newPassword;
  18 |   }
  19 |   
  20 |   public void setNewPassword(String newPassword) {
  21 |     this.newPassword = newPassword;
  22 |   }
  23 |   
  24 |   public String getConfirmNewPassword() {
  25 |     return confirmNewPassword;
  26 |   }
  27 |   
  28 |   public void setConfirmNewPassword(String confirmNewPassword) {
  29 |     this.confirmNewPassword = confirmNewPassword;
  30 |   }
  31 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/dto/UserDTO.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.dto;
   2 | 
   3 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.UserEntity;
   4 | 
   5 | public class UserDTO {
   6 | 	
   7 | 	private Long id;
   8 | 	private String username;
   9 | 	private String fullName;
  10 | 	private String email;
  11 | 	
  12 | 	public UserDTO(UserEntity user) {
  13 | 		this.id = user.getId();
  14 | 		this.username = user.getUserName();
  15 | 		this.fullName = user.getFullName();
  16 | 		this.email = user.getEmail();
  17 | 	}
  18 | 	
  19 | 	public UserDTO() {
  20 | 		
  21 | 	}
  22 | 	
  23 | 	public Long getId() {
  24 | 		return id;
  25 | 	}
  26 | 
  27 | 	public void setId(Long id) {
  28 | 		this.id = id;
  29 | 	}
  30 | 
  31 | 	public String getUsername() {
  32 | 		return username;
  33 | 	}
  34 | 
  35 | 	public void setUsername(String username) {
  36 | 		this.username = username;
  37 | 	}
  38 | 
  39 | 	public String getFullName() {
  40 | 		return fullName;
  41 | 	}
  42 | 
  43 | 	public void setFullName(String fullName) {
  44 | 		this.fullName = fullName;
  45 | 	}
  46 | 	
  47 | 	public String getEmail() {
  48 | 		return email;
  49 | 	}
  50 | 	
  51 | 	public void setEmail(String email) {
  52 | 		this.email = email;
  53 | 	}
  54 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/entity/StandardFlashcardEntity.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.entity;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
   6 | import jakarta.persistence.Column;
   7 | import jakarta.persistence.DiscriminatorValue;
   8 | import jakarta.persistence.Entity;
   9 | 
  10 | @Entity
  11 | @DiscriminatorValue("STANDARD_FLASHCARD")
  12 | public class StandardFlashcardEntity extends FlashcardEntity {
  13 | 
  14 |   @Column(nullable = false)
  15 | 	private String front;
  16 | 	
  17 | 	@Column(nullable = false)
  18 | 	private String back;
  19 | 
  20 |   @Column(nullable = false)
  21 | 	private LocalDateTime nextReview;
  22 | 	
  23 | 	// Quantidade de vezes que o card foi revisado com sucesso (sequencialmente).
  24 | 	// Inicia em zero.
  25 | 	@Column(nullable = false)
  26 | 	private int repetition;
  27 | 	
  28 | 	// Grau de facilidade do flashcard.
  29 | 	// Começa com 2.5 e muda conforme o desempenho do usuário
  30 | 	@Column(nullable = false)
  31 | 	private double easeFactor;
  32 | 	
  33 | 	//Quantidade de dias até a próxima revisão.
  34 | 	//Começa em 1 e cresce conforme o algoritmo.
  35 | 	// interval *= easeFactor;
  36 | 	@Column(nullable = false)
  37 | 	private int interval;
  38 | 
  39 |   public StandardFlashcardEntity() {
  40 |     super();
  41 |     super.setFlashcardType("STANDARD_FLASHCARD");
  42 |   }
  43 | 
  44 |   public String getFront() {
  45 |     return front;
  46 |   }
  47 | 
  48 |   public void setFront(String front) {
  49 |     this.front = front;
  50 |   }
  51 | 
  52 |   public String getBack() {
  53 |     return back;
  54 |   }
  55 | 
  56 |   public void setBack(String back) {
  57 |     this.back = back;
  58 |   }
  59 | 
  60 |   public LocalDateTime getNextReview() {
  61 |     return nextReview;
  62 |   }
  63 | 
  64 |   public void setNextReview(LocalDateTime nextReview) {
  65 |     this.nextReview = nextReview;
  66 |   }
  67 | 
  68 |   public int getRepetition() {
  69 |     return repetition;
  70 |   }
  71 | 
  72 |   public void setRepetition(int repetition) {
  73 |     this.repetition = repetition;
  74 |   }
  75 | 
  76 |   public double getEaseFactor() {
  77 |     return easeFactor;
  78 |   }
  79 | 
  80 |   public void setEaseFactor(double easeFactor) {
  81 |     this.easeFactor = easeFactor;
  82 |   }
  83 | 
  84 |   public int getInterval() {
  85 |     return interval;
  86 |   }
  87 | 
  88 |   public void setInterval(int interval) {
  89 |     this.interval = interval;
  90 |   }
  91 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/handler/StandardFlashcardHandler.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.handler;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import br.com.TrabalhoEngSoftware.chatbot.config.Constants;
   6 | import br.com.TrabalhoEngSoftware.chatbot.dto.StandardFlashcardDTO;
   7 | import br.com.TrabalhoEngSoftware.chatbot.dto.StandardUserAnswerDTO;
   8 | import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
   9 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.InvalidObjectDataException;
  10 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnexpectedResponseException;
  11 | import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeHandler;
  12 | 
  13 | public class StandardFlashcardHandler implements FlashcardTypeHandler<StandardFlashcardDTO, StandardFlashcardEntity, StandardUserAnswerDTO> {
  14 |   
  15 |   @Override
  16 |   public String supportsType() {
  17 |     return "STANDARD_FLASHCARD";
  18 |   }
  19 | 
  20 |   @Override
  21 |   public StandardFlashcardEntity createFlashcard(StandardFlashcardDTO dto) {
  22 |     StandardFlashcardEntity flashcard = new StandardFlashcardEntity();
  23 |     flashcard.setFront(dto.getFront());
  24 |     flashcard.setBack(dto.getBack());
  25 |     flashcard.setNextReview(LocalDateTime.now());
  26 |     flashcard.setRepetition(0);
  27 |     flashcard.setEaseFactor(2.5);
  28 |     flashcard.setInterval(1);
  29 |     return flashcard;
  30 |   }
  31 | 
  32 |   @Override
  33 |   public void updateFlashcard(StandardFlashcardEntity flashcard, StandardFlashcardDTO dto) {
  34 |     if(dto.getFront() == null || dto.getFront().trim().isEmpty()) {
  35 |       throw new InvalidObjectDataException("Front flashcard can't be empty");
  36 |     }
  37 | 
  38 |     flashcard.setFront(dto.getFront());
  39 | 
  40 |     if(dto.getBack() != null) {
  41 |       flashcard.setBack(dto.getBack());
  42 |     }
  43 |   }
  44 | 
  45 |   @Override
  46 |   public int evaluateAnswer(StandardFlashcardEntity flashcard, StandardUserAnswerDTO answer) {
  47 |     int[] possiblesAnswer = {Constants.WRONG, Constants.HARD, Constants.GOOD, Constants.EASY};
  48 |     for(int possibleAnswer : possiblesAnswer){
  49 |       if(answer.getAnswer() == possibleAnswer) return answer.getAnswer();
  50 |     }
  51 |     throw new UnexpectedResponseException("This response is invalid.");
  52 |   }
  53 | 
  54 |   @Override
  55 |   public StandardFlashcardDTO entityToDTO(StandardFlashcardEntity flashcard) {
  56 |     StandardFlashcardDTO dto = new StandardFlashcardDTO();
  57 |     dto.setFront(flashcard.getFront());
  58 |     dto.setBack(flashcard.getBack());
  59 |     dto.setCreatedAt(flashcard.getCreatedAt());
  60 |     dto.setLastReviewedAt(flashcard.getLastReviewedAt());
  61 |     dto.setNextReview(flashcard.getNextReview());
  62 |     dto.setRepetition(flashcard.getRepetition());
  63 |     dto.setEaseFactor(flashcard.getEaseFactor());
  64 |     dto.setInterval(flashcard.getInterval());
  65 |     return dto;
  66 |   }
  67 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/handler/StandardFlashcardSearchHandler.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.handler;
   2 | 
   3 | import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
   4 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeSearchHandler;
   6 | import jakarta.persistence.criteria.CriteriaBuilder;
   7 | import jakarta.persistence.criteria.Predicate;
   8 | import jakarta.persistence.criteria.Root;
   9 | 
  10 | public class StandardFlashcardSearchHandler implements FlashcardTypeSearchHandler {
  11 |   
  12 |   @Override
  13 |   public String supportsType() {
  14 |     return "STANDARD_FLASHCARD";
  15 |   }
  16 | 
  17 |   @Override
  18 |   public Predicate getWordSearchPredicate(Root<FlashcardEntity> root, CriteriaBuilder criteriaBuilder, String word) {
  19 |     Predicate wordPredicate = criteriaBuilder.conjunction();
  20 |     Root<StandardFlashcardEntity> standardFlashcardRoot = criteriaBuilder.treat(root, StandardFlashcardEntity.class);
  21 |     if(word != null && !word.isEmpty()) {
  22 | 			wordPredicate = criteriaBuilder.or(
  23 |         criteriaBuilder.like(criteriaBuilder.lower(standardFlashcardRoot.get("front")), "%" + word.toLowerCase() + "%"),
  24 |         criteriaBuilder.like(criteriaBuilder.lower(standardFlashcardRoot.get("back")), "%" + word.toLowerCase() + "%")
  25 |       );
  26 | 		}
  27 |     return criteriaBuilder.and(wordPredicate);
  28 |   }
  29 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/repository/FlashcardAppRepository.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.repository;
   2 | 
   3 | import java.time.LocalDateTime;
   4 | 
   5 | import org.springframework.data.domain.Page;
   6 | import org.springframework.data.domain.Pageable;
   7 | import org.springframework.data.jpa.repository.Query;
   8 | import org.springframework.data.repository.query.Param;
   9 | 
  10 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
  11 | import br.com.TrabalhoEngSoftwareFramework.framework.repository.FlashcardRepository;
  12 | 
  13 | public interface FlashcardAppRepository extends FlashcardRepository {
  14 |   
  15 |   @Query("SELECT f FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId AND f.deckEntity.userEntity.id = :userId AND TREAT(f AS StandardFlashcardEntity).nextReview < :tomorrow ORDER BY TREAT(f AS StandardFlashcardEntity).nextReview ASC")
  16 |   Page<FlashcardEntity> findNextDueFlashcardByDeckId(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("tomorrow") LocalDateTime tomorrow, Pageable pageable);
  17 | 
  18 |   @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId AND f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt IS NULL")
  19 |   long countNewFlashcards(@Param("deckId") Long deckId, @Param("userId") Long userId);
  20 | 
  21 |   @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId  AND f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt BETWEEN :startOfDay AND :endOfDay AND TREAT(f AS StandardFlashcardEntity).nextReview BETWEEN :startOfDay AND :endOfDay")
  22 |   long countLearningFlashcards(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
  23 | 
  24 |   @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.id = :deckId  AND f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt < :startOfToday AND TREAT(f AS StandardFlashcardEntity).nextReview BETWEEN :startOfToday AND :endOfToday")
  25 |   long countReviewFlashcards(@Param("deckId") Long deckId, @Param("userId") Long userId, @Param("startOfToday") LocalDateTime startOfToday, @Param("endOfToday") LocalDateTime endOfToday);
  26 | 
  27 |   @Query("SELECT f FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND TREAT(f AS StandardFlashcardEntity).nextReview < :tomorrow ORDER BY TREAT(f AS StandardFlashcardEntity).nextReview ASC")
  28 |   Page<FlashcardEntity> findNextDueFlashcard(@Param("userId") Long userId, @Param("tomorrow") LocalDateTime tomorrow, Pageable pageable);
  29 | 
  30 |   @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt IS NULL")
  31 |   long countAllNewFlashcards(@Param("userId") Long userId);
  32 | 
  33 |   @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt BETWEEN :startOfDay AND :endOfDay AND TREAT(f AS StandardFlashcardEntity).nextReview BETWEEN :startOfDay AND :endOfDay")
  34 |   long countAllLearningFlashcards(@Param("userId") Long userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
  35 | 
  36 |   @Query("SELECT COUNT(f) FROM FlashcardEntity f WHERE f.deckEntity.userEntity.id = :userId AND f.lastReviewedAt < :startOfToday AND TREAT(f AS StandardFlashcardEntity).nextReview BETWEEN :startOfToday AND :endOfToday")
  37 |   long countAllReviewFlashcards(@Param("userId") Long userId, @Param("startOfToday") LocalDateTime startOfToday, @Param("endOfToday") LocalDateTime endOfToday);
  38 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/repository/SourceRepository.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.repository;
   2 | 
   3 | import org.springframework.data.jpa.repository.JpaRepository;
   4 | 
   5 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.SourceEntity;
   6 | 
   7 | import java.util.List;
   8 | 
   9 | public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
  10 |     // Encontra todas os arquivos associados ao ID especificado
  11 |     List<SourceEntity> findByNoteEntityId(Long noteId);
  12 | 
  13 |     // Encontra um arquivo específico associado ao ID do arquivo e ao ID da nota (para confirmar que o arquivo pertence à nota)
  14 |     SourceEntity findByIdAndNoteEntityId(Long sourceId, Long noteId);
  15 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/service/DeckAppService.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.service;
   2 | 
   3 | import java.time.LocalDate;
   4 | import java.time.LocalDateTime;
   5 | 
   6 | import org.springframework.stereotype.Service;
   7 | import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
   8 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
   9 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.ObjectNotFoundException;
  10 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnauthorizedObjectAccessException;
  11 | import br.com.TrabalhoEngSoftwareFramework.framework.repository.DeckRepository;
  12 | import br.com.TrabalhoEngSoftwareFramework.framework.service.DeckService;
  13 | 
  14 | @Service
  15 | public class DeckAppService extends DeckService {
  16 | 
  17 |   public DeckAppService(DeckRepository deckRepository) {
  18 |     super(deckRepository);
  19 |   }
  20 | 
  21 |   public long getDueFlashcardsTotal(Long deckId, Long userId) {
  22 |     DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
  23 |     if(!deck.getUserEntity().getId().equals(userId)) {
  24 | 			throw new UnauthorizedObjectAccessException("Unauthorized to see due flashcards total this deck");
  25 | 		}
  26 |     LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
  27 |     long dueFlashcardsTotal = deck.getFlashcards().stream()
  28 |                                   .filter(flashcard -> {
  29 |                                     if(flashcard.getFlashcardType().equals("STANDARD_FLASHCARD")) {
  30 |                                       StandardFlashcardEntity standardFlashcard = (StandardFlashcardEntity) flashcard;
  31 |                                       return standardFlashcard.getNextReview().isBefore(tomorrow);
  32 |                                     }
  33 |                                     return false;
  34 |                                   })
  35 |                                   .count();
  36 |     return dueFlashcardsTotal;
  37 |   }
  38 | 
  39 |   public double getMasteryLevel(Long deckId, Long userId) {
  40 |     DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
  41 |     if(!deck.getUserEntity().getId().equals(userId)) {
  42 | 			throw new UnauthorizedObjectAccessException("Unauthorized to see mastery level this deck");
  43 | 		}
  44 | 
  45 |     int repetitionMastery = 4;
  46 |     long dominatedFlashcards = deck.getFlashcards().stream()
  47 |                             .filter(flashcard -> {
  48 |                               if(flashcard.getFlashcardType().equals("STANDARD_FLASHCARD")) {
  49 |                                 StandardFlashcardEntity standardFlashcard = (StandardFlashcardEntity) flashcard;
  50 |                                 return standardFlashcard.getRepetition() >= repetitionMastery;
  51 |                               }
  52 |                               return false;
  53 |                             })
  54 |                             .count();
  55 | 
  56 |     int flashcardsTotal = deck.getFlashcards().size();
  57 |     if (flashcardsTotal == 0) return 0.0; 
  58 | 
  59 |     double masteryLevel = (double) dominatedFlashcards/flashcardsTotal;
  60 |     return masteryLevel;
  61 |   } 
  62 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/service/FlashcardAppService.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.service;
   2 | 
   3 | import java.time.LocalDate;
   4 | import java.time.LocalDateTime;
   5 | import java.time.LocalTime;
   6 | import java.util.Optional;
   7 | 
   8 | import org.springframework.data.domain.Page;
   9 | import org.springframework.data.domain.PageRequest;
  10 | import org.springframework.beans.factory.annotation.Autowired;
  11 | import org.springframework.stereotype.Service;
  12 | import org.springframework.transaction.annotation.Transactional;
  13 | 
  14 | import br.com.TrabalhoEngSoftware.chatbot.config.Constants;
  15 | import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
  16 | import br.com.TrabalhoEngSoftware.chatbot.repository.FlashcardAppRepository;
  17 | import br.com.TrabalhoEngSoftwareFramework.framework.dto.FlashcardDTO;
  18 | import br.com.TrabalhoEngSoftwareFramework.framework.dto.UserAnswerDTO;
  19 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
  20 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
  21 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.ObjectNotFoundException;
  22 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnauthorizedObjectAccessException;
  23 | import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeHandler;
  24 | import br.com.TrabalhoEngSoftwareFramework.framework.service.FlashcardService;
  25 | 
  26 | @Service
  27 | public class FlashcardAppService extends FlashcardService {
  28 | 
  29 |   @Autowired
  30 |   private FlashcardAppRepository flashcardAppRepository;
  31 | 
  32 |   public FlashcardAppService() {
  33 |     super();
  34 |   }
  35 | 
  36 |   public Optional<FlashcardDTO> getNextDueFlashcardByDeckId(Long deckId, Long userId) {
  37 |     LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
  38 |     Page<FlashcardEntity> page = flashcardAppRepository.findNextDueFlashcardByDeckId(deckId, userId, tomorrow, PageRequest.of(0, 1));
  39 |     return page.stream().findFirst().map(flashcardEntity -> {
  40 |       FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO> handler = handlerRegistry.getHandler(flashcardEntity.getFlashcardType());
  41 |       return handler.entityToDTO(flashcardEntity);
  42 |     });
  43 |   }
  44 | 
  45 |   @Transactional
  46 |   public void applyReviewResult(Long flashcardId, UserAnswerDTO userAnswer, Long userId) {
  47 |     FlashcardEntity flashcard = flashcardAppRepository.findById(flashcardId).orElseThrow(() -> new ObjectNotFoundException("Flashcard not found"));
  48 | 
  49 |     if(!flashcard.getDeckEntity().getUserEntity().getId().equals(userId)) {
  50 |       throw new UnauthorizedObjectAccessException("Unauthorized to review this flashcard");
  51 |     }
  52 | 
  53 |     int answer = evaluateAnswer(flashcardId, userAnswer, userId);
  54 |     
  55 |     if(flashcard.getFlashcardType().equals("STANDARD_FLASHCARD")) {
  56 |       StandardFlashcardService standardFlashcard = new StandardFlashcardService();
  57 |       standardFlashcard.applyReview((StandardFlashcardEntity) flashcard, answer);
  58 |     }
  59 | 
  60 |     flashcardRepository.save(flashcard);
  61 |   }
  62 | 
  63 |   protected double calculateEaseFactor(double easeFactor, int answer) {
  64 |     double easeFactorTemp = easeFactor - 0.8 + (0.28*answer) - (0.02*Math.pow(answer,2));
  65 |     return Math.max(Constants.MIN_EASE_FACTOR, easeFactorTemp);
  66 |   }
  67 | 
  68 |   @Transactional
  69 |   public long getCountNewFlashcards(Long deckId, Long userId) {
  70 |     DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
  71 |     if(!deck.getUserEntity().getId().equals(userId)) {
  72 |       throw new UnauthorizedObjectAccessException("Unauthorized to count the learning flashcards this deck.");
  73 |     }
  74 |     return flashcardAppRepository.countNewFlashcards(deckId, userId);
  75 |   }
  76 | 
  77 |   @Transactional
  78 |   public long getCountLearningFlashcards(Long deckId, Long userId) {
  79 |     DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
  80 |     if(!deck.getUserEntity().getId().equals(userId)) {
  81 |       throw new UnauthorizedObjectAccessException("Unauthorized to count the learning flashcards this deck.");
  82 |     }
  83 |     LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
  84 |     LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
  85 |     return flashcardAppRepository.countLearningFlashcards(deckId, userId, startOfToday, endOfToday);
  86 |   }
  87 | 
  88 |   @Transactional
  89 |   public long getCountReviewFlashcards(Long deckId, Long userId) {
  90 |     DeckEntity deck = deckRepository.findById(deckId).orElseThrow(() -> new ObjectNotFoundException("Deck not found"));
  91 |     if(!deck.getUserEntity().getId().equals(userId)) {
  92 |       throw new UnauthorizedObjectAccessException("Unauthorized to count the learning flashcards this deck.");
  93 |     }
  94 |     LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
  95 |     LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
  96 |     return flashcardAppRepository.countReviewFlashcards(deckId, userId, startOfToday, endOfToday);
  97 |   }
  98 | 
  99 |   public Optional<FlashcardDTO> getNextDueFlashcard(Long userId) {
 100 |     LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
 101 |     Page<FlashcardEntity> page = flashcardAppRepository.findNextDueFlashcard(userId, tomorrow, PageRequest.of(0, 1));
 102 | 
 103 |     return page.stream().findFirst().map(flashcardEntity -> {
 104 |       FlashcardTypeHandler<FlashcardDTO, FlashcardEntity, UserAnswerDTO> handler = handlerRegistry.getHandler(flashcardEntity.getFlashcardType());
 105 |       return handler.entityToDTO(flashcardEntity);
 106 |     });
 107 |   }
 108 | 
 109 |   @Transactional
 110 |   public long getCountAllNewFlashcards(Long userId) {
 111 |     return flashcardAppRepository.countAllNewFlashcards(userId);
 112 |   }
 113 | 
 114 |   @Transactional
 115 |   public long getCountAllLearningFlashcards(Long userId) {
 116 |     LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
 117 |     LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
 118 |     return flashcardAppRepository.countAllLearningFlashcards(userId, startOfToday, endOfToday);
 119 |   }
 120 | 
 121 |   @Transactional
 122 |   public long getCountAllReviewFlashcards(Long userId) {
 123 |     LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
 124 |     LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
 125 |     return flashcardAppRepository.countAllReviewFlashcards(userId, startOfToday, endOfToday);
 126 |   }
 127 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/service/StandardFlashcardService.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.service;
   2 | 
   3 | import java.time.LocalDate;
   4 | import java.time.LocalDateTime;
   5 | import java.time.LocalTime;
   6 | 
   7 | import br.com.TrabalhoEngSoftware.chatbot.config.Constants;
   8 | import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
   9 | import br.com.TrabalhoEngSoftwareFramework.framework.exception.UnexpectedResponseException;
  10 | import br.com.TrabalhoEngSoftwareFramework.framework.service.FlashcardService;
  11 | 
  12 | public class StandardFlashcardService extends FlashcardService {
  13 |   
  14 |   public StandardFlashcardService() {
  15 |     super();
  16 |   }
  17 | 
  18 |   public void applyReview(StandardFlashcardEntity flashcard, int answer) {
  19 |     LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
  20 |     double easeFactor = flashcard.getEaseFactor();
  21 | 
  22 |     if(answer != Constants.WRONG && answer != Constants.HARD && answer != Constants.GOOD && answer != Constants.EASY) {
  23 |       throw new UnexpectedResponseException("Unexpected response");
  24 |     }
  25 | 
  26 |     if(answer == Constants.WRONG) {
  27 |       flashcard.setRepetition(0);
  28 |       if(LocalDateTime.now().plusMinutes(1L).isBefore(tomorrow)){
  29 |         flashcard.setNextReview(LocalDateTime.now().plusMinutes(1L));
  30 |       } else {
  31 |         flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
  32 |       }
  33 |       flashcard.setInterval(1);
  34 |       flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
  35 |     } else {
  36 |       flashcard.setRepetition(flashcard.getRepetition()+1);
  37 |       if(flashcard.getRepetition() == 1){
  38 |         if(answer == Constants.HARD) {
  39 |           if(LocalDateTime.now().plusMinutes(5L).isBefore(tomorrow)){
  40 |             flashcard.setNextReview(LocalDateTime.now().plusMinutes(5L));
  41 |           } else {
  42 |             flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
  43 |           }
  44 |           flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
  45 |         }
  46 |         if(answer == Constants.GOOD) { 
  47 |           if(LocalDateTime.now().plusMinutes(10L).isBefore(tomorrow)){
  48 |             flashcard.setNextReview(LocalDateTime.now().plusMinutes(10L));
  49 |           } else {
  50 |             flashcard.setNextReview(LocalDate.now().atTime(LocalTime.MAX));
  51 |           }
  52 |         }
  53 |         if(answer == Constants.EASY) {
  54 |           flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
  55 |           flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
  56 |           flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
  57 |         }
  58 |       } else {
  59 |         if(flashcard.getInterval() == 1 && flashcard.getRepetition() == 2) {
  60 |           flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
  61 |         } else {
  62 |           flashcard.setEaseFactor(calculateEaseFactor(easeFactor, answer));
  63 |           flashcard.setInterval((int) Math.ceil(flashcard.getInterval()*flashcard.getEaseFactor()));
  64 |           flashcard.setNextReview(LocalDateTime.now().plusDays(flashcard.getInterval()));
  65 |         }
  66 |       }
  67 |     }
  68 | 
  69 |     flashcard.setLastReviewedAt(LocalDateTime.now());
  70 |     flashcard.getDeckEntity().setLastReviewedAt(LocalDateTime.now());
  71 |   }
  72 | 
  73 |   private double calculateEaseFactor(double easeFactor, int answer) {
  74 |     double easeFactorTemp = easeFactor - 0.8 + (0.28*answer) - (0.02*Math.pow(answer,2));
  75 |     return Math.max(Constants.MIN_EASE_FACTOR, easeFactorTemp);
  76 |   }
  77 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/specification/DeckSpecification.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.specification;
   2 | 
   3 | import java.time.LocalDate;
   4 | import java.time.LocalDateTime;
   5 | 
   6 | import br.com.TrabalhoEngSoftware.chatbot.entity.StandardFlashcardEntity;
   7 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.DeckEntity;
   8 | import br.com.TrabalhoEngSoftwareFramework.framework.entity.FlashcardEntity;
   9 | import br.com.TrabalhoEngSoftwareFramework.framework.specification.DeckSpecificationBuilder;
  10 | import jakarta.persistence.criteria.Expression;
  11 | import jakarta.persistence.criteria.JoinType;
  12 | import jakarta.persistence.criteria.ListJoin;
  13 | 
  14 | public class DeckSpecification extends DeckSpecificationBuilder {
  15 | 	
  16 | 	public DeckSpecification(String title, String topic) {
  17 | 		super(title, topic);
  18 | 
  19 | 		buildSpecification("totalDueFlashcardsDesc", (root, query, criteriaBuilder) -> {
  20 | 			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
  21 | 
  22 | 			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
  23 |           criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);
  24 | 
  25 | 			LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay(); 
  26 | 			Expression<Long> dueFlashcardsTotal = criteriaBuilder.sum(
  27 | 				criteriaBuilder.<Long>selectCase()
  28 | 				.when(criteriaBuilder.lessThan(standardFlashcardJoin.get("nextReview"), tomorrow), 1L)
  29 | 				.otherwise(0L)
  30 | 			);
  31 | 
  32 | 			query.groupBy(root.get("id"));
  33 | 			query.orderBy(criteriaBuilder.desc(dueFlashcardsTotal));
  34 | 			return null;	
  35 | 		});
  36 | 
  37 | 		buildSpecification("totalDueFlashcardsAsc", (root, query, criteriaBuilder) -> {
  38 | 			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
  39 | 
  40 | 			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
  41 |           criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);
  42 | 
  43 | 			LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay(); 
  44 | 			Expression<Long> dueFlashcardsTotal = criteriaBuilder.sum(
  45 | 				criteriaBuilder.<Long>selectCase()
  46 | 				.when(criteriaBuilder.lessThan(standardFlashcardJoin.get("nextReview"), tomorrow), 1L)
  47 | 				.otherwise(0L)
  48 | 			);
  49 | 
  50 | 			query.groupBy(root.get("id"));
  51 | 			query.orderBy(criteriaBuilder.asc(dueFlashcardsTotal));
  52 | 			return null;	
  53 | 		});
  54 | 
  55 | 		buildSpecification("masteryLevelDesc", (root, query, criteriaBuilder) -> {
  56 | 			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
  57 | 			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
  58 | 				criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);
  59 | 
  60 | 			int repetitionMastery = 4;
  61 | 			Expression<Long> dominatedFlashcards = criteriaBuilder.sum(
  62 | 				criteriaBuilder.<Long>selectCase()
  63 | 				.when(criteriaBuilder.greaterThanOrEqualTo(standardFlashcardJoin.get("repetition"), repetitionMastery), 1L)
  64 | 				.otherwise(0L)
  65 | 			);
  66 | 
  67 | 			Expression<Long> flashcardsTotal = criteriaBuilder.count(standardFlashcardJoin);
  68 | 			Expression<Number> masteryLevel = criteriaBuilder.quot(criteriaBuilder.toDouble(dominatedFlashcards), criteriaBuilder.toDouble(flashcardsTotal));
  69 | 			query.groupBy(root.get("id"));
  70 | 			query.orderBy(criteriaBuilder.desc(masteryLevel));
  71 | 			return null;
  72 | 		});
  73 | 
  74 | 		buildSpecification("masteryLevelAsc", (root, query, criteriaBuilder) -> {
  75 | 			ListJoin<DeckEntity, FlashcardEntity> flashcardJoin = root.joinList("flashcards", JoinType.LEFT);
  76 | 			ListJoin<DeckEntity, StandardFlashcardEntity> standardFlashcardJoin =
  77 | 				criteriaBuilder.treat(flashcardJoin, StandardFlashcardEntity.class);
  78 | 
  79 | 			int repetitionMastery = 4;
  80 | 			Expression<Long> dominatedFlashcards = criteriaBuilder.sum(
  81 | 				criteriaBuilder.<Long>selectCase()
  82 | 				.when(criteriaBuilder.greaterThanOrEqualTo(standardFlashcardJoin.get("repetition"), repetitionMastery), 1L)
  83 | 				.otherwise(0L)
  84 | 			);
  85 | 
  86 | 			Expression<Long> flashcardsTotal = criteriaBuilder.count(standardFlashcardJoin);
  87 | 			Expression<Number> masteryLevel = criteriaBuilder.quot(criteriaBuilder.toDouble(dominatedFlashcards), criteriaBuilder.toDouble(flashcardsTotal));
  88 | 			query.groupBy(root.get("id"));
  89 | 			query.orderBy(criteriaBuilder.asc(masteryLevel));
  90 | 			return null;
  91 | 		});
  92 | 	}
  93 | }

```

`chatbot/src/main/java/br/com/TrabalhoEngSoftware/chatbot/specification/FlashcardSpecification.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot.specification;
   2 | 
   3 | import java.time.LocalDate;
   4 | import java.time.LocalDateTime;
   5 | 
   6 | import br.com.TrabalhoEngSoftwareFramework.framework.handler.FlashcardTypeSearchRegistry;
   7 | import br.com.TrabalhoEngSoftwareFramework.framework.specification.FlashcardSpecificationBuilder;
   8 | 
   9 | public class FlashcardSpecification extends FlashcardSpecificationBuilder {
  10 |   
  11 | 	public FlashcardSpecification(FlashcardTypeSearchRegistry searchRegistry) {
  12 | 		super(searchRegistry);
  13 | 
  14 | 		buildSpecification("dominatedFlashcards", (root, query, criteriaBuilder) -> {
  15 | 			int repetitionMastery = 4;
  16 |       return criteriaBuilder.greaterThanOrEqualTo(root.get("repetition"), repetitionMastery); 
  17 | 		});
  18 | 
  19 | 		buildSpecification("undominatedFlashcards", (root, query, criteriaBuilder) -> {
  20 | 			int repetitionMastery = 4;
  21 |       return criteriaBuilder.lessThan(root.get("repetition"), repetitionMastery);
  22 | 		});
  23 | 
  24 | 		buildSpecification("newFlashcards", (root, query, criteriaBuilder) -> {
  25 | 			return criteriaBuilder.isNull(root.get("lastReviewedAt"));
  26 | 		});
  27 | 
  28 | 		buildSpecification("notNewFlashcards", (root, query, criteriaBuilder) -> {
  29 | 			return criteriaBuilder.isNotNull(root.get("lastReviewedAt"));
  30 | 		});
  31 | 
  32 | 		buildSpecification("dueFlashcards", (root, query, criteriaBuilder) -> {
  33 |       LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
  34 | 			return criteriaBuilder.lessThan(root.get("nextReview"), tomorrow);
  35 | 		});
  36 | 
  37 | 		buildSpecification("nextReviewAsc", (root, query, criteriaBuilder) -> {
  38 |       query.orderBy(criteriaBuilder.asc(root.get("nextReview")));
  39 | 			return null;
  40 | 		});
  41 | 
  42 | 		buildSpecification("nextReviewDesc", (root, query, criteriaBuilder) -> {
  43 |       query.orderBy(criteriaBuilder.desc(root.get("nextReview")));
  44 | 			return null;
  45 | 		});
  46 | 	}
  47 | }

```

`chatbot/src/main/resources/application.properties`:

```properties
   1 | spring.application.name=chatbot
   2 | spring.datasource.url=jdbc:postgresql://localhost:5432/chatbot
   3 | spring.datasource.username=postgres
   4 | spring.datasource.password=password
   5 | 
   6 | spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   7 | spring.jpa.hibernate.ddl-auto=create-drop
   8 | spring.jpa.show-sql=true
   9 | 
  10 | api.security.token.secret=${JWT_SECRET:mySecretKey}
  11 | spring.ai.openai.chat.base-url=https://generativelanguage.googleapis.com
  12 | spring.ai.openai.chat.completions-path=/v1beta/openai/chat/completions
  13 | spring.ai.openai.api-key=${GEMINI_API_KEY}
  14 | spring.ai.openai.chat.options.model=gemini-2.0-flash
  15 | spring.main.allow-bean-definition-overriding=true

```

`chatbot/src/test/java/br/com/TrabalhoEngSoftware/chatbot/ChatbotApplicationTests.java`:

```java
   1 | package br.com.TrabalhoEngSoftware.chatbot;
   2 | 
   3 | import org.junit.jupiter.api.Test;
   4 | import org.springframework.boot.test.context.SpringBootTest;
   5 | 
   6 | @SpringBootTest
   7 | class ChatbotApplicationTests {
   8 | 
   9 | 	@Test
  10 | 	void contextLoads() {
  11 | 	}
  12 | 
  13 | }

```