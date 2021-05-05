all:
	mvn install -P linux-amd64
	mvn install -P macos-amd64
	mvn install -P windows-amd64