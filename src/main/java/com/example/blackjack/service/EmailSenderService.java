package com.example.blackjack.service;

import com.example.blackjack.dto.stats.Estatisticas;
import com.example.blackjack.entity.Usuario;
import com.example.blackjack.repository.UsuarioRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final UsuarioRepository usuarioRepository;
    private final JogoService jogoService;
    @Value("${email.origem}")
    private String origemEmail;

    public void enviarEmailAgradecimento(String email) {
        jogoService.verificarPartidaEmAndamento(email);
        Estatisticas estatisticas = jogoService.getEstatisticas(email);
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
            helper.setTo(usuario.getEmail());
            helper.setFrom(origemEmail);
            helper.setSubject("Obrigado por jogar Blackjack 21 🎰");
            helper.setText(montarEmailEstatisticas(usuario.getNome(), estatisticas), true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email", e);
        }
    }

    public String montarEmailEstatisticas(String nome, Estatisticas estatisticas) {
        return """
                <div style="font-family: Arial, sans-serif; background-color:#0b0f1a; padding:20px;">
                    <div style="max-width:600px; margin:auto; background:#111827; border-radius:12px; overflow:hidden;">
                
                        <div style="background: linear-gradient(90deg,#facc15,#f43f5e); padding:20px; text-align:center;">
                            <h1 style="margin:0; color:#0b0f1a; font-size:22px;">
                                ♠ BLACKJACK CASINO ♥
                            </h1>
                        </div>
                
                        <div style="padding:20px; color:#e5e7eb;">
                
                            <h2 style="color:#facc15;">Olá, %s 🎲</h2>
                
                            <p style="font-size:15px; color:#cbd5e1;">
                                Obrigado por jogar conosco! Aqui está o resumo das suas partidas:
                            </p>
                
                            <div style="margin-top:20px;">
                
                                <div style="background:#0f172a; padding:15px; border-radius:10px; margin-bottom:10px;">
                                    <span style="color:#22c55e; font-weight:bold;">Vitórias</span>
                                    <h2 style="margin:5px 0;">%d</h2>
                                </div>
                
                                <div style="background:#0f172a; padding:15px; border-radius:10px; margin-bottom:10px;">
                                    <span style="color:#f97316; font-weight:bold;">Empates</span>
                                    <h2 style="margin:5px 0;">%d</h2>
                                </div>
                
                                <div style="background:#0f172a; padding:15px; border-radius:10px;">
                                    <span style="color:#ef4444; font-weight:bold;">Derrotas</span>
                                    <h2 style="margin:5px 0;">%d</h2>
                                </div>
                
                            </div>
                
                            <div style="margin-top:25px; text-align:center;">
                                <p style="font-size:14px; color:#94a3b8;">
                                    Obrigado por fazer parte do <b>Blackjack Casino</b> 🎰<br>
                                    Boa sorte nas próximas rodadas!
                                </p>
                            </div>
                
                        </div>
                    </div>
                </div>
                """.formatted(nome, estatisticas.getVitorias(), estatisticas.getEmpates(), estatisticas.getDerrotas());
    }
}