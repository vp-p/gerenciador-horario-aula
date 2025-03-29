<html>
<head><meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> </head>

<body>
<div align = "center">
<img src = "images/dctBanner.png" width = 100%></div>

<br>

# Planejamento de Sprints
<details>

<summary><strong>Sprint 1</strong></summary>

## User Story

| Rank | Prioridade | ID   | User Story    | Story Points | Sprint | Requisitos do Parceiro |
|------|------------|----------|-------------------------------------------------------------------------------------------------------|--------------|--------|-------|
| 1    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 6  | Como coordenador, quero visualizar a grade de aulas com horários em tabela, para possibilitar a fácil identificação e distribuição de disciplinas e horários. | 3            | 1      | 8 |

### Requisitos:

- A aplicação deve conter a grade horária das aulas em uma tabela para melhor identificação e distruibuição de disciplina;
- Os dados na tabela devem conter apenas os professores, as disciplinas e os horários de um determinado curso.

### Definition of Done (DoD):
 - Código da aplicação devidamente versionado, organizado, funcionando e disponibilizado no Git Hub.
 - Gerenciamento da equipe definido e implementado na criação de telas.
 - Funcionalidade revisada por pelo menos um membro da equipe (Code Rewiew). 
 - Funcionalidade testada e aprovada.
 - Funcionalidade integrada ao Sistema.

 ### Definition of Ready (DoR):
- User Story priorizada e apta para ser desenvolvida.
- User Story atende aos critérios de aceitação.
- Tarefas definidas, atribuidas e relacionadas com as User Stories.
- Na grade de aulas, será possível visualizar aulas dividas em campos da tabela.

### WireFrame



</details>

<details>
<summary><strong>Sprint 2</strong></summary>

## User Story

| Rank | Prioridade | ID   | User Story    | Story Points | Sprint | Requisitos do Parceiro|
|------|------------|------|---------------|--------------|--------|-----------------------|
|2     |![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 7 | Como coordenador, quero cadastrar professores para consultar seus dados rapidamente, facilitando alocação de turmas. | 3 | 2 | 8 |

### Requisitos:

- O coordernador deve ter a funcionalidade de cadastrar o professor com as informações Nome e Email.
- Para a consulta, deve ser feita em tabelas para melhor visualização.
- O coordenador deve ter a funcionalidade de excluir informações de professores enviadas indevidamente.
- Deve ter uma filtragem de dados nas tabelas para encontrar os dados dos professores.

### Definition of Done (DoD):

 - Código da aplicação devidamente versionado, organizado, funcionando e disponibilizado no Git Hub.
 - Gerenciamento da equipe definido e implementado na criação de telas.
 - Funcionalidade revisada por pelo menos um membro da equipe (Code Rewiew). 
 - Funcionalidade testada e aprovada.
 - Funcionalidade integrada ao Sistema.

 ### Definition of Ready (DoR):

- User Story priorizada e apta para ser desenvolvida.
- User Story atende aos critérios de aceitação.
- Tarefas definidas, atribuidas e relacionadas com as User Stories.
- No cadastro do professor, teremos campos para inserção do nome e email de cada professor.


### WireFrame

---
<br>

| Rank | Prioridade | ID  | User Story | Story Points | Sprint | Requisitos do Parceiro|
|------|------------|-----|------------|--------------|--------|-----------------------|
| 3    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 8  | Como coordenador, quero cadastrar aulas no sistema para evitar choques de horários.                   | 5           | 2      | 8 | 

### Requisitos:

- Ao cadastrar novas aulas, o mesmo professor não pode estar em várias aulas ao mesmo tempo.
- O mesmo professor leciona no máximo 10 aulas em um dia, portanto deve ter 11 horas de descanso apartir da última aula lecionada.
- Deve haver uma sugestão de alocação de horário, caso haja duplicidade do mesmo professor em um mesmo horário em várias aulas.
- Todas as aulas precisam ter 50 minutos.

### Definition of Done (DoD):

 - Código da aplicação devidamente versionado, organizado, funcionando e disponibilizado no Git Hub.
 - Gerenciamento da equipe definido e implementado na criação de telas.
 - Funcionalidade revisada por pelo menos um membro da equipe (Code Rewiew). 
 - Funcionalidade testada e aprovada.
 - Funcionalidade integrada ao Sistema.

 ### Definition of Ready (DoR):

- User Story priorizada e apta para ser desenvolvida.
- User Story atende aos critérios de aceitação.
- Tarefas definidas, atribuidas e relacionadas com as User Stories.
- Será possível adicionar uma nova aula com informações de inicio do horário, fim do horário e dia da semana.
- Em uma aula, o coordenador irá inserir dados relacionados sobre o professor, disciplina, curso e horário. 

### Wireframe


| <center >Validações </center>| <center>Retorno do Cliente</center> |
|------------------------------|-------------------------------------|
|O registro do intervalo entra como uma aula? Que teria o tempo reduzido?| Toda aula precisa ter 50 minutos. |

---
<br>

| Rank | Prioridade | ID       | User Story | Story Points | Sprint | Requisitos do Parceiro |
|------|----------------------------------------------------------------------|----------|------------------|--------------|--------|--------|
| 4    | ![#ff0000](https://via.placeholder.com/15/ff0000/000000?text=+) ALTA | SIN - 9  | Como coordenador, quero cadastrar cursos para garantir que todas as disciplinas estejam vinculadas a uma estrutura curricular específica. | 3            | 2      | 4 |


</details>
<br>

# Diagrama de Entidade e Relacionamento
<img src = "BD_DER.png">

</body>
</html>