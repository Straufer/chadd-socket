package vaadin.views.chat;

import com.paung.service.ChatService;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.Command;
import jakarta.annotation.security.PermitAll;
import vaadin.views.MainLayout;

import java.util.ArrayList;

@PageTitle("Chat")
@Route(value = "chat", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class ChatView extends HorizontalLayout {
    public ChatView(ChatService chatService) {
        var list_msg = new MessageList();
        var input_user = new MessageInput();

        setSizeFull();
        add(
                list_msg,
                input_user
        );
        expand(list_msg);

        input_user.setWidthFull();

        chatService.join().subscribe((message) -> {
            var inject_msgList = new ArrayList<>(list_msg.getItems());
            inject_msgList.add(new MessageListItem(message.msg(), message.time(), message.username()));
            getUI().ifPresent(ui -> ui.access((Command) () -> list_msg.setItems(inject_msgList)));

        });


    }

}
