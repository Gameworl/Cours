package iut.lp.dna.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import iut.lp.dna.chat.model.Message;
import iut.lp.dna.chat.model.User;

public class MessageView extends FrameLayout {
    public MessageView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.view_msg, this);
    }

    @SuppressLint("SetTextI18n")
    public void populate(@NonNull Message message, User user){
        TextView usernameText = findViewById(R.id.username);
        TextView messageText = findViewById(R.id.message);
        TextView positionText = findViewById(R.id.position);

        messageText.setText(message.getText());

        if (user == null){
            usernameText.setText("inconnue");
            positionText.setVisibility(GONE);
        } else {
            usernameText.setText(user.getName());
            if (user.getPosition() == null){
                positionText.setVisibility(GONE);
            }else {
                positionText.setVisibility(VISIBLE);
                positionText.setText("(" + user.getPosition().getLongitude() + ',' + user.getPosition().getLatitude() + ")");
            }
        }
    }
}
