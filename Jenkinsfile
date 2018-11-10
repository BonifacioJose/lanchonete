node {
   def mvnHome
   stage('Preparation') { 
      git (url: 'https://github.com/BonifacioJose/lanchonete', branch: 'master')

      mvnHome = tool 'M3'
   }
   stage('Build') {
      dir('backend') {
          if (isUnix()) {
             sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
          } else {
             bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
          }
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
}