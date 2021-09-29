<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");

    // 파라미터 조회
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    // Member 객체 생성
    Member member = new Member(username, age);

    // Member 객체를 MemberRepository 를 통해서 저장
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공

    <ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>

    <a href="/index.html">메인</a>
</body>
</html>
