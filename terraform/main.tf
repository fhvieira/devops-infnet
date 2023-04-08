provider "aws" {
  region = "us-east-1"
}

resource "aws_instance" "cliente-api" {
  ami           = "ami-0c55b159cbfafe1f0"
  instance_type = "t2.micro"
  key_name      = "cliente-api-key"

  connection {
    type        = "ssh"
    user        = "ec2-user"
    private_key = file("~/.ssh/example-key.pem")
    host        = self.public_ip
  }

  provisioner "file" {
    source      = "../app-cliente/target/cliente-0.0.1-SNAPSHOT.jar"
    destination = "/app/myapp.jar"
  }

  user_data = <<-EOF
              #!/bin/bash
              sudo yum update -y
              sudo yum install java-1.8.0-openjdk -y
              sudo mkdir /app
              cd /app
              sudo java -jar myapp.jar
              EOF

  tags = {
    Name = "example-instance"
  }
}
