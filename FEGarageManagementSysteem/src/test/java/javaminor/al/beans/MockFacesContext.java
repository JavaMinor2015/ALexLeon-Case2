package javaminor.al.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Created by alex on 11/22/15.
 * <p>
 * http://illegalargumentexception.blogspot
 * .com/2011/12/jsf-mocking-facescontext-for-unit-tests
 * .html#mockFacesCurrentInstance
 */
public abstract class MockFacesContext extends FacesContext {
    private MockFacesContext() {
    }

    private static final Release RELEASE = new Release();

    @Override
    public void addMessage(final String var1, final FacesMessage var2) {
        // do a silly dance
    }

    private static class Release implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            setCurrentInstance(null);
            return null;
        }
    }

    public static FacesContext mockFacesContext() {
        FacesContext context = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        Mockito.doAnswer(RELEASE)
                .when(context)
                .release();
        return context;
    }
}
