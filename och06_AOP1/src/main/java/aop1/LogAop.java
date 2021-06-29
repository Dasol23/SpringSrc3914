package aop1;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop { //loggerAop 프록시 역할을 할 애..?
	// Around Advice에서 사용할 공통기능 메서드는, 대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출
		public Object loggerAop(ProceedingJoinPoint joinpoint) {
			//핵심업무에 사용되는 메소드
			String signatureStr = joinpoint.getSignature().toString();
			long st = System.currentTimeMillis();
			System.out.println(signatureStr + "is start..");
			
			try {
				//핵심업무수행
				Object obj =  joinpoint.proceed();
				return obj;
			} catch (Throwable e) {
				
				e.printStackTrace();
			}finally {
				long et = System.currentTimeMillis();
				System.out.println(signatureStr + "is finished.");
				System.out.println(signatureStr + "경과시간: " + (et - st));
			}
			return st;
			
		}
}
