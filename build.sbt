name := "Activate Eager Test"

version := "1"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "net.fwbrasil"      %% "activate-core"       % "1.5-M4"
, "net.fwbrasil"      %% "activate-jdbc"       % "1.5-M4"
, "org.postgresql"    %  "postgresql"          % "9.3-1100-jdbc41"
)
