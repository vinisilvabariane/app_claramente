package com.claramente.feature.hub.state

object HubFeed {
    val items: List<FeedItem> = listOf(
        FeedItem(
            id = "1",
            tag = "Aviso",
            tone = FeedTone.NOTICE,
            title = "A Sala dos professores publicou um novo comunicado sobre a reunião de pais de sexta-feira.",
            time = "há 2 horas",
        ),
        FeedItem(
            id = "2",
            tag = "Atividade",
            tone = FeedTone.ACTIVITY,
            title = "Nova atividade disponível: \"Mapa mental de atenção e presença\".",
            time = "há 5 horas",
        ),
        FeedItem(
            id = "3",
            tag = "Conquista",
            tone = FeedTone.ACHIEVEMENT,
            title = "Você concluiu 68% da trilha \"Fundamentos de psicologia\". Continue assim!",
            time = "ontem",
        ),
        FeedItem(
            id = "4",
            tag = "Lembrete",
            tone = FeedTone.REMINDER,
            title = "Sua próxima aula ao vivo começa amanhã às 14h.",
            time = "ontem",
        ),
    )
}
