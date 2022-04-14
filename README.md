# SeleniumFinalTask

mvn clean test -Denvironment=${environment} -Dbrowser=${browser} -DbrowserVersion=${browserVersion} -Dplatform=${platform}

1. Environment:
  - local (default)
  - grid
  - sauceLab

2. Browser:
  - chrome (default)
  - firefox
  - "other" (according to sauceLab documentation)

3. BrowserVersion (only for sauceLab):
  - latest (default)
  - "other" (according to sauceLab documentation)

4. Platform
  - windows 10 (default)
  - windows 8.1
  - "other" (according to sauceLab documentation)
