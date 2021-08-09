package main

import (
	"fmt"
	"os"
	"os/exec"
	"runtime"
)

const (
	LINUX       = "linux"
	Windows     = "windows"
	CurrentEnv  = runtime.GOOS
	JarName     = "markdown-toc-tool.jar"
	runJar      = "java -jar "
	cmd         = "cmd"
	cmdPrefix   = "/c"
	shell       = "/bin/sh"
	shellPrefix = "-c"
)

func main() {
	javaHome := os.Getenv("JAVA_HOME") + "/bin/"
	mdTocHome := os.Getenv("MDTOC_HOME")

	args := getArgs()

	jarPath := mdTocHome + "/lib/"

	command := javaHome + runJar + jarPath + JarName

	if CurrentEnv == LINUX {
		run(shell, shellPrefix, command+args)
	} else {
		run(cmd, cmdPrefix, command+args)
	}
}

// util func start =====================

// getArgs 获取用户输入的命令行参数
func getArgs() string {
	args := " "
	for _, arg := range os.Args[1:] {
		args = args + " " + arg
	}
	return args
}

// run 执行命令
func run(name string, arg ...string) {
	command := exec.Command(name, arg...)
	output, err := command.CombinedOutput()
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(string(output))
}
