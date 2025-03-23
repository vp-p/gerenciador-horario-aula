<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
A equipe <strong>SINAPSE</strong>, estudantes do 2º Semestre do curso de Banco de Dados da Fatec São José dos Campos, está comprometida com o desenvolvimento do projeto "Sistema de Gerenciamento de Horários Fatec" neste repositório. O objetivo do projeto é criar uma aplicação que permita o coordenador da Fatec definir horários específicos para cada professor. O projeto inclui a modelagem de dados relacional e aplicação da linguagem Java.</br>

# Índice
♦ [Requisitos](#requisitos)</br>
♦ [Integrantes do Projeto](#integrantes-do-projeto)</br>
♦ [Cronograma do Projeto](#cronograma-do-projeto)</br>

## Requisitos

### Requisitos Funcionais
<ol>
<li>Definição de horário de aula para um semestre do curso, lembrando que cada disciplina possui uma quantidade de aulas obrigatória por semana. Exemplo: A disciplina Arquitetura e Modelagem de Banco de Dados possui 4 aulas por semana, portanto cada aula precisa ser definida individualmente;</li>
<li>Mecanismo para evitar sobreposição de horários (mesmo professor com disciplinas diferentes em um mesmo horário);</li>
<li>Sugestão de alocação, levando em conta os horários de aula já definidos;</li>
<li>Gerenciamento de semestre letivo, permitindo definir quais disciplinas fazem parte do semestre de um determinado curso;</li>
<li>Gerenciamento de cursos;</li>
<li>Gerenciamento de disciplinas;</li>
<li>Gerenciamento de professores;</li>
<li>Gerenciamento de horários. Apesar da hora aula ser padronizada em 50 minutos, cada curso pode definir o posicionamento das aulas de forma diferente.</li>
</ol>

### Requisitos Não Funcionais
<ol>
<li>Manual do Usuário;</li>
<li>Guia de Instalação;</li>
<li>Modelo Entidade Relacionamento.</li>
</ol>


## Integrantes do Projeto 
| <center>Linkedin & Git hub</center> | Integrante | Função | Identificação |
|------------------------------------ |------------|--------|---------------|
|<a href="https://www.linkedin.com/in/johnatan-coelho-de-jesus-souza-30bbb62a5/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin" ></a> <a href="https://github.com/JohnatanCoelho"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a>| Johnatan Coelho | Product Owner | <a href=""><center><img src="assets/Dev Team Profile/Johnatan.jpeg" height="55" width="55"></a></center> |
|<a href="http://www.linkedin.com/in/viniciuspenteadop"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin"></a> <a href="https://github.com/vp-p"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a>| Vinicius Penteado | Scrum Master | <a href=""><center><img src="assets/Dev Team Profile/Vinicius.jpeg" height="55" width="55"></a></center> |
|<a href="https://www.linkedin.com/in/haoyunvj/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin"></a> <a href="https://github.com/haoyunvj"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> | João Vitor | Developer | <center><img src="assets/Dev Team Profile/Joao.jpeg" height="55" width="55"></a></center> |
|<a href="https://www.linkedin.com/in/davi-gramacho-702a48326/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin"></a> <a href="https://github.com/DaviGramacho"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> | Davi Gramacho | Developer | <center><img src="assets/Dev Team Profile/Davi.jpeg" height="60" width="55"></a></center> |
|<a href="https://www.linkedin.com/in/giovana-zucareli-1aa205202/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin"></a> <a href="https://github.com/GiovanaZucareli"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> | Giovana Zucareli | Developer | <center><img src="assets/Dev Team Profile/Giovana.jpg" height="55" width="55"></a></center> |
|<a href="https://www.linkedin.com/in/isaac-de-oliveira-841057255/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin"></a> <a href="https://github.com/IsaacOliveiraSouza"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> | Isaac de Oliveira | Developer | <center><img src="assets/Dev Team Profile/Isaac.png" height="55" width="55"></a></center> |
|<a href="https://www.linkedin.com/in/victor-rodrigues-00a247178/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="Linkedin"></a> <a href="https://github.com/Victorvmor"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> | Victor Miguel | Developer | <center><img src="assets/Dev Team Profile/Victor.jpg" height="55" width="55"></a></center> |

## Backlog do Produto

| Rank | Prioridade | ID       | User Story                                                                                           | Story Points | Sprint |
|------|------------|----------|-------------------------------------------------------------------------------------------------------|--------------|--------|
| 1    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 6  | Como coordenador, quero visualizar a grade de aulas com horários em tabela, para possibilitar a fácil identificação e distribuição de disciplinas e horários. | 3            | 1      |
| 2    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 7  | Como coordenador, quero cadastrar professores para consultar seus dados rapidamente, facilitando alocação de turmas. | 3            | 2      |
| 3    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 8  | Como coordenador, quero cadastrar aulas no sistema para evitar choques de horários.                   | 5           | 2      |
| 4    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 9  | Como coordenador, quero cadastrar cursos para garantir que todas as disciplinas estejam vinculadas a uma estrutura curricular específica. | 3            | 2      |
| 5    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 10 | Como coordenador, quero cadastrar disciplinas no sistema para facilitar a alocação eficiente de professores. | 3            | 2      |
| 6    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 11 | Como coordenador, quero cadastrar coordenadores para cada curso para que o curso funcione de forma eficiente, alinhado aos objetivos institucionais e às normas educacionais. | 3            | 2      |
| 7    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 12 | Como coordenador, quero cadastrar semestres para organizar os cursos em níveis de aprendizado, permitindo uma progressão clara e estruturada no desenvolvimento dos alunos ao longo do curso. | 5            | 3      |
| 8    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 13 | Como coordenador, quero poder exportar a grade horária para Excel, para facilitar a acessibilidade e portabilidade dos horários. | 3            | 3      |
| 9    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 14 | Como coordenador, quero fazer login no sistema, com e-mail e senha, para que eu possa acessar as funcionalidades e gerenciar as informações de forma segura. | 3            | 2     |
| 10   | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 15 | Como coordenador, quero poder alterar minha senha para que eu possa manter minha conta segura e atualizada. | 5            | 3      |


## Cronograma do Projeto 
| Sprints  |   Início   | Entrega    | Status  |
| -------- | ---------- | ---------- | ------- |
| Kick-Off | 26/02/2025 | 28/02/2025 |   ✅   |
| Sprint 1 | 10/03/2025 | 30/03/2025 |   ⬜   |
| Sprint 2 | 07/04/2025 | 27/04/2025 |   ⬜   |
| Sprint 3 | 05/05/2025 | 25/05/2025 |   ⬜   |
| Feira de Soluções| 29/05/2025 | 29/05/2025 | ⬜ |

## Tecnologia Utilizadas
![Jira](https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white) 
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Termos de Requisitos de Permanência

### Objetivo: 

 - Estabelecer critérios mínimos que os membros da equipe *SINAPSE* devem cumprir para manter sua participação ativa. 

- Garantir a continuidade, qualidade e sucesso do projeto, bem como promover o comprometimento e a responsabilidade de todos os envolvidos.

### 1 - Reuniões

- Comparecer às reuniões semanais agendadas em todas as segundas e quartas, durante as aulas designadas para API.
- Justificar eventuais ausências ao grupo.
- A presença nas reuniões é essencial para o compartilhamento de informações e alinhamento das atividades do projeto.

### 2 - Curso Obrigatórios

- Todos os participantes do projeto devem realizar e concluir os cursos de Java POO (Programação Orientada a Objetos) e SQL.
- A conclusão desses cursos é essencial para a compreensão e execução eficiente das tarefas do projeto.
- O não cumprimento desta exigência pode impactar negativamente a participação no projeto.

### 3 - Comunicação com o Time
- Manter uma comunicação efetiva e constante com o time, utilizando os canais oficiais de comunicação: WhatsApp , Slack, Discord ou comunicação presencial.
- Ausências de comunicação podem prejudicar o andamento do projeto e comprometer os resultados esperados.
- É responsabilidade de cada membro informar qualquer situação que possa afetar sua capacidade de contribuir ao projeto.

### 4 - Prazos de entrega
- É fundamental respeitar prazos de entregas estabelecidos pelo time.
- Os Atrasos de entregas devem ser evitados a todo custo, caso tenha alguma dificuldade informe o time.
- A ausência de entregas podem causar efeitos negativos ao trabalho da equipe e o andamento do projeto.
</body>
</html>
