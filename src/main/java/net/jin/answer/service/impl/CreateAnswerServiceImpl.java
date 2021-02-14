package net.jin.answer.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.answer.service.CreateAnsewerService;
import net.jin.model.Answer;
import net.jin.model.Question;
import net.jin.model.User;
import net.jin.repository.AnswerRepository;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;

/*IoC컨테이너에 등록되어 관리되는 객체를 빈(Bean)이라고 합니다. 
New 생성자를 이용해 생성한 객체는 IoC컨테이너에 등록된 객체가 아니므로 빈이 아니다

<빈(Bean) 등록방법 3가지>
1. Application context.xml(root-context.xml)에 등록 (Bean id와 클래스 명, 변수 이름, 의존관계 등을 xml로 작성하는 방법) 
2. 컴포넌트 스캔 : 빈으로 등록할 클래스에 어노테이션으로 명시해준다. 스프링은 컴포넌트 어노테이션이 붙은 클래스들을 스캔하여 자동으로 IoC컨테이너에 등록해주는 어노테이션 프로세서가 존재합니다.   
* 컴포넌트 어노테이션  
@Component : 기본형   
@Repository : 데이터 접근 객체   
@Service : 서비스 객체   
@Controller : 컨트롤러 객체   
@Repository, @service, @Controller는 역할 구분을 위해 @Component를 재정의한 것입니다.
3. Java Config class
자바 클래스에 @Configuration 어노테이션으로 설정 파일임을 명시합니다.   

<DI (Dependency Injection, 의존성 주입)>
각 클래스 사이에 필요로 하는 의존관계를 빈 설정 정보를 바탕으로 컨테이너가 자동으로 연결해 주는 것입니다.
<의존성 주입 방법 3가지>
1. XML에 등록
Application context(root-context).xml에 빈으로 등록하면서 의존관계를 명시해줍니다. ref속성으로 의존관계를 명시할 수 있습니다.
2. Java Config class에 등록
Java Config class를 만들어주고 빈으로 등록하면서 의존관계를 명시해줍니다. 빈을 인자로 받아 생성되도록 작성할 수 있다. 다음 소스는 위의 XML등록 방법과 대응됩니다.
3. 어노테이션을 이용
어노테이션을 사용해 빈으로 등록된 의존 객체를 주입합니다. 다음과 같은 어노테이션을 사용할 수 있습니다.
@Resource - .객체 이름을 우선으로 DI대상을 연결한합니다. 자바에서 지원하므로 프레임워크에 종속적이지 않습니다.
@Autowired - 객체 타입을 우선으로 DI대상을 연결합니다. 스프링에서 지원하므로 프레임워크에 종속적입니다.
@Inject - 객체 타입을 우선으로 DI대상을 연결합니다. 자바에서 지원하므로 프레임워크에 종속적이지 않습니다.
*/

@Service // 빈등록 컴포넌트 어노테이션 자동으로 IOC컨테이너에 등록
public class CreateAnswerServiceImpl implements CreateAnsewerService {
	
	@Autowired //DI 어노테이션, new를 통해서 객체화 필요 없음
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Override
	public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
		System.out.println("**************Start API createAnswer************");
		if (!HttpSessionUtils.isLoginUser(session)) {
			System.out.println("**************You are Not login User************");
			return null;
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);

		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, question, contents);
		question.addAnswer();
		return answerRepository.save(answer);
	}

}
