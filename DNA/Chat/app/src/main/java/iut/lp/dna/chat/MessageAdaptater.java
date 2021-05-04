package iut.lp.dna.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import iut.lp.dna.chat.model.Message;
import iut.lp.dna.chat.model.User;

public class MessageAdaptater extends ArrayAdapter<Message> {

    private List<User> users;
    public MessageAdaptater(@NonNull Context context) {
        super(context, R.layout.view_msg, new ArrayList<>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View converterView, @NonNull ViewGroup parent){
        MessageView messageView;

        if (converterView == null){
            messageView = new MessageView((getContext()));
        }else {
            messageView= (MessageView) converterView;
        }

        Message message = getItem(position);
        User userMessage = null;
        if (users != null){
            for (User user : users){
                if (user.getId().equals(message.getUser())){
                    userMessage = user;
                    break;
                }
            }
        }
        messageView.populate(message, userMessage);
        return  messageView;
    }

    public void setUsers(List<User> users){
        this.users = users;

    }

    public void setMessage(List<Message> message){
        this.clear();
        this.addAll(message);
    }


}
